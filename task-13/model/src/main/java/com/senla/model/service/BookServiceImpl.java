package com.senla.model.service;

import com.senla.config.annotations.Component;
import com.senla.config.annotations.MyAutoWired;
import com.senla.config.annotations.MyInject;
import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.repository.api.BookRepository;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.ExportHelper;
import com.senla.model.сomparators.BookAlphabeticalComparator;
import com.senla.model.сomparators.BookArriveDataComparator;
import com.senla.model.сomparators.BookPriceComparator;
import com.senla.model.сomparators.BookPublicationDataComparator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    @MyAutoWired
    private BookRepository bookRepository;
    @MyAutoWired
    private RequestForBookService requestForBookService;
    @MyAutoWired
    private OrderService orderService;
    @MyInject(key = "bookFile")
    private String path;
    @MyInject(key = "maxCountOfMonth")
    private String maxCountOfMonthString;
    @MyAutoWired
    private RequestForBookRepository requestForBookRepository;
    static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    public void customSearch(String author, LocalDate endDate) {
        List<Book> bookList = bookRepository.getAll();
        bookList.stream()
                .filter(x -> x.getAuthor().contains(author))
                .filter(x -> x.getPublicationDate().isBefore(endDate))
                .sorted()
                .forEach(x -> logger.debug(x));
    }

    public void importBook(){
        List<Book> bookList = bookRepository.getAll();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String author = strings[1];
                String title = strings[2];
                double price = Double.parseDouble(strings[3]);
                String arriveDateString = strings[4];
                String publicationDateString = strings[5];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
                LocalDate publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);
                LocalDate arriveDate = LocalDate.parse(arriveDateString, dateTimeFormatter);
                Book book = createBook(new Book(id, title, author, price, arriveDate, publicationDate));
                if (bookList.get(id).getId() == book.getId()) {
                    bookUpdate(book);
                } else {
                    bookRepository.create(book);
                }
            }
        } catch (IOException | SQLException e) {
            logger.error("We have no file");
        }
    }

    public boolean bookInStockChecker(String titleBook, String authorBook){
        List<Book> bookList = getListOfBooksInStoreHouse();
        boolean flag = false;
        for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(titleBook)){
                if(bookList.get(i).getAuthor().equals(authorBook)){
                    flag = true;
                }
            }
        }
        return flag;
    }

    public Book getBookByAuthorAndTitle(String titleBook, String authorBook){
        List<Book> bookList = getListOfBooksInStoreHouse();
        Book book = null;
        for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(titleBook) & bookList.get(i).getAuthor().equals(authorBook)){
                book = bookList.get(i);
            }
        }
        return book;
    }

    public void exportBook(){
        List<Book> bookList = getListOfBooksInStoreHouse();
        ExportHelper.write(null, bookList, null, null, path);
    }

    public Book createBook(Book book) throws SQLException {
        bookRepository.create(book);
        System.out.println("Book has been created");
        completingRequestAfterArrivingNewBook(book);
        return book;
    }
    public List<Book> getListOfBooksInStoreHouse(){
       List<Book> books = bookRepository.getAll();
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

       public void completingRequestAfterArrivingNewBook(Book book) {
        List<RequestForBook> requestForBooks = requestForBookService.getListOfRequestForBook();
           for (int i = 0; i < requestForBooks.size(); i++) {
              if(requestForBooks.get(i).getTitleOfBook().equals(book.getTitle()) & requestForBooks.get(i).getAuthorOfBook().equals(book.getAuthor())) {
                  requestForBooks.get(i).setRequestStatus(RequestForBookStatus.COMPLETED);
                  requestForBookRepository.update(requestForBooks.get(i));
                  Order order = orderService.read(requestForBooks.get(i).getOrder().getId());
                  orderService.executeOrder(order);
              }
           }
    }

    public void showUnsoldBooksMoreThanSixMonth() {
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
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
        logger.debug("Book has been deleted");
    }

    public void showBooksInStock(){
        List<Book> books = bookRepository.getAll();
        logger.debug("List of the books in stock");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + ";" + books.get(i).getAuthor() + "; " +  i);
        }

    }

    public void sortBookByPrice() {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookPriceComparator);
        logger.debug("List of books sorted by price: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPrice());
        }
    }

    public void sortBookByAuthor() {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAlphabeticalComparator);
        logger.debug("List of books sorted by author: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " +books.get(i).getAuthor());
        }
    }

    public void sortBookByDateArrive() {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookDataComparator);
        logger.debug("List of books sorted by date of arrive: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }

    public void sortBookByPublicationDate() {
        BookPublicationDataComparator bookAvailabilityComparator = new BookPublicationDataComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAvailabilityComparator);
        logger.debug("List of books sorted by date of publication: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }

    public Book getBookById(int id){
        Book book = bookRepository.read(id);
        return book;
    }

}
