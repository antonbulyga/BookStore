package com.senla.model.service;

import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.repository.api.BookRepository;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.ExportHelper;
import com.senla.model.сomparators.BookAlphabeticalComparator;
import com.senla.model.сomparators.BookArriveDataComparator;
import com.senla.model.сomparators.BookPriceComparator;
import com.senla.model.сomparators.BookPublicationDataComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private RequestForBookService requestForBookService;
    private OrderService orderService;
    @Value("${bookFile}")
    private String path;
    @Value("${maxCountOfMonth}")
    private String maxCountOfMonthString;
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Autowired
    public void setRequestForBookService(RequestForBookService requestForBookService) {
        this.requestForBookService = requestForBookService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Book> customSearch(String author, LocalDate endDate) {
        List<Book> bookList = bookRepository.getAll();
        List<Book> result = new ArrayList<>();
        bookList.stream()
                .filter(x -> x.getAuthor().contains(author))
                .filter(x -> x.getPublicationDate().isBefore(endDate))
                .sorted()
                .collect(Collectors.toCollection(() -> result));
        return result;
    }

    @Transactional
    public void importBook() {
        List<Book> bookList = bookRepository.getAll();
        List<Order> orderList = orderService.getListOfOrders();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String author = strings[1];
                double price = Double.parseDouble(strings[3]);
                String title = strings[2];
                String arriveDateString = strings[4];
                String publicationDateString = strings[5];
                int orderId = Integer.parseInt(strings[6]);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);
                LocalDate arriveDate = LocalDate.parse(arriveDateString, dateTimeFormatter);
                Book book = new Book(title, author, price, arriveDate, publicationDate);
                book.setId(id);
                book.setOrder(orderList.get(orderId));
                for (int i = 0; i < bookList.size(); i++) {
                    if(book.getId() == bookList.get(i).getId()){
                        bookRepository.update(book);
                        count++;
                    }
                }
                if (count == 0) {
                    bookRepository.create(book);
                }
                count = 0;
            }
        } catch (IOException | SQLException e) {
            logger.error("We have no file");
        }
    }

    public boolean bookInStockChecker(String titleBook, String authorBook) {
        List<Book> bookList = getListOfBooksInStoreHouse();
        boolean flag = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(titleBook)) {
                if (bookList.get(i).getAuthor().equals(authorBook)) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public Book getBookByAuthorAndTitle(String titleBook, String authorBook) {
        List<Book> bookList = getListOfBooksInStoreHouse();
        Book book = null;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(titleBook) & bookList.get(i).getAuthor().equals(authorBook)) {
                book = bookList.get(i);
            }
        }
        return book;
    }

    @Transactional
    public void exportBook() {
        List<Book> bookList = getListOfBooksInStoreHouse();
        ExportHelper.write(null, bookList, null, null, path);
    }

    public Book createBook(Book book) throws SQLException {
        bookRepository.create(book);
        System.out.println("Book has been created");
        completingRequestAfterArrivingNewBook(book);
        return book;
    }

    public List<Book> getListOfBooksInStoreHouse() {
        List<Book> books = bookRepository.getAll();
        if (books.isEmpty()) {
            throw new NoResultException("No books in the database");
        }
        return books;
    }

    public void addBookToListOfBookInTheStorehouse(Book book) throws SQLException {
        bookRepository.create(book);
        logger.debug("Book has been created");
    }

    public void bookUpdate(Book book) {
        bookRepository.update(book);
        logger.debug("Book has been updated");
    }

    @Transactional
    public void completingRequestAfterArrivingNewBook(Book book) { {
            List<RequestForBook> requestForBooks = requestForBookService.getListOfRequestForBook();
            for (int i = 0; i < requestForBooks.size(); i++) {
                if (requestForBooks.get(i).getTitleOfBook().equals(book.getTitle()) & requestForBooks.get(i).getAuthorOfBook().equals(book.getAuthor())) {
                    requestForBooks.get(i).setRequestStatus(RequestForBookStatus.COMPLETED);
                    requestForBookService.update(requestForBooks.get(i));
                    List<Order> orderList = orderService.getListOfOrders();
                    for (int j = 0; j < orderList.size(); j++) {
                        if(orderList.get(j).getId() == requestForBooks.get(i).getOrder().getId()){
                            orderService.executeOrder(orderList.get(j));
                        }
                    }
                }
            }
        }
    }

    public List<Book> showUnsoldBooksMoreThanSixMonth() {
        LocalDate date = LocalDate.now();
        List<Book> books = bookRepository.getAll();
        List<Book> arrayOfUnsoldBooksMoreThanSixMonth = new ArrayList<>();
        logger.debug("Books unsold for more than 6 month : ");
        for (int i = 0; i < books.size(); i++) {
            LocalDate date2 = books.get(i).getArriveDate().plusMonths(6);
            int compareResult = date2.compareTo(date);
            if (compareResult < 0) {
                arrayOfUnsoldBooksMoreThanSixMonth.add(books.get(i));
            }

        }

        for (int i = 0; i < arrayOfUnsoldBooksMoreThanSixMonth.size(); i++) {
            logger.error(arrayOfUnsoldBooksMoreThanSixMonth.get(i).getTitle());
        }
        return arrayOfUnsoldBooksMoreThanSixMonth;
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
        logger.debug("Book has been deleted");
    }

    public void showBooksInStock() {
        List<Book> books = bookRepository.getAll();
        logger.debug("List of the books in stock");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + ";" + books.get(i).getAuthor() + "; " + i);
        }

    }

    public List<Book> sortBookByPrice() {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookPriceComparator);
        logger.debug("List of books sorted by price: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPrice());
        }
        return books;
    }

    public List<Book> sortBookByAuthor() {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAlphabeticalComparator);
        logger.debug("List of books sorted by author: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getAuthor());
        }
        return books;
    }

    public List<Book> sortBookByDateArrive() {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookDataComparator);
        logger.debug("List of books sorted by date of arrive: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
        return books;
    }

    public List<Book> sortBookByPublicationDate() {
        BookPublicationDataComparator bookAvailabilityComparator = new BookPublicationDataComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAvailabilityComparator);
        logger.debug("List of books sorted by date of publication: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPublicationDate());
        }
        return books;
    }

    public Book getBookById(int id) {
        Book book = bookRepository.read(id);
        if(book == null) {
            throw new NoResultException("No book with this ID");
        }
        return book;
    }

}
