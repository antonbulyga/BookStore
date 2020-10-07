package main.java.com.senla.model.service;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.enumeration.RequestForBookStatus;
import main.java.com.senla.model.repository.api.BookRepository;
import main.java.com.senla.model.repository.api.OrderRepository;
import main.java.com.senla.model.service.api.BookService;
import main.java.com.senla.model.service.api.OrderService;
import main.java.com.senla.model.service.api.RequestForBookService;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.utils.generators.StockLevelIdGenerator;
import main.java.com.senla.model.сomparators.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    @MyAutoWired
    private BookRepository bookRepository;
    @MyAutoWired
    private OrderRepository orderRepository;
    @MyAutoWired
    private RequestForBookService requestForBookService;
    @MyAutoWired
    private OrderService orderService;
    @MyInject(key = "bookFile")
    private String path;
    @MyInject(key = "maxCountOfMonth")
    private String maxCountOfMonthString;

    public void customSearch(String author, LocalDate endDate) {
        List<Book> bookList = bookRepository.getAll();
        bookList.stream()
                .filter(x -> x.getAuthor().contains(author))
                .filter(x -> x.getPublicationDate().isBefore(endDate))
                .sorted()
                .forEach(x -> System.out.println(x));
    }

    public void importBook(){
        List<Book> bookList = bookRepository.getAll();
        List<RequestForBook> requestForBooks = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String author = strings[1];
                String title = strings[2];
                double price = Double.parseDouble(strings[3]);
                String bookStatus = strings[4];
                String arriveDateString = strings[5];
                String publicationDateString = strings[6];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
                LocalDate publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);
                LocalDate arriveDate = LocalDate.parse(arriveDateString, dateTimeFormatter);
                Book book = createBook(new Book(id, title, author, price, BookStatus.IN_STOCK, requestForBooks, arriveDate, publicationDate));
                if (bookList.get(id).getId() == book.getId()) {
                    bookUpdate(book);
                } else {
                    bookRepository.create(book);
                }
            }
        } catch (IOException | SQLException e) {
            System.err.println("We have no file");
        }
    }

    public void exportBook(){
        List<Book> bookList = getListOfBooksInStoreHouse();
        ExportHelper.write(null, bookList, null, null, path);
    }

    public Book createBook(Book book) throws SQLException {
        List<RequestForBook> requestForBookList = new ArrayList<>();
        bookRepository.create(book);
        book.setRequestForBooks(requestForBookList);
        requestForBookService.closerRequestForBooksAfterArrivingBook(book);
        //completingRequestAfterArrivingNewBook(book);
        return book;
    }
    public List<Book> getListOfBooksInStoreHouse(){
       List<Book> books = bookRepository.getAll();
       return books;
    }

    public void addBookToListOfBookInTheStorehouse(Book book) throws SQLException {
        bookRepository.create(book);
    }

    public void bookUpdate(Book book) {
        bookRepository.update(book);
    }

    public List<Book> setStaleBookStatus(){
        int countOfMonthToMarkBookAsStale = getCountOfMonthToMarkBookAsStale();
        List<Book> books = bookRepository.getAll();
        List<Book> listOfStaleBooks = new ArrayList<>();
        LocalDate arriveDate = null;
        LocalDate nowDate = arriveDate.now();
        for (int i = 0; i < books.size(); i++) {
            arriveDate = books.get(i).getArriveDate();
            long countOfMonth = ChronoUnit.MONTHS.between(arriveDate, nowDate);
            if(countOfMonth > countOfMonthToMarkBookAsStale){
                books.get(i).setBookStatus(BookStatus.STALE);
                listOfStaleBooks.add(books.get(i));
            }
        }
        return listOfStaleBooks;
    }

    public void showStaleBooks(){
        List<Book> listOfStaleBooks = setStaleBookStatus();
        for (int i = 0; i < listOfStaleBooks.size(); i++) {
            System.out.println(listOfStaleBooks.get(i) + " " + i);
        }
    }

    public int getCountOfMonthToMarkBookAsStale(){
        int countOfMonthToMarkBookAsStale = 0;
        String countOfMonthToMarkBookAsStaleString = maxCountOfMonthString;
        try {
            countOfMonthToMarkBookAsStale = Integer.parseInt(countOfMonthToMarkBookAsStaleString);
        }
        catch (NumberFormatException e){
            System.out.println("The data in property is incorrect");
        }
        return countOfMonthToMarkBookAsStale;
    }

    public void completingRequestAfterArrivingNewBook(Book book) {
        List<RequestForBook> requestForBooks = requestForBookService.getListOfRequestForBook();
        List<RequestForBook> requestForBooksLocal;
        Order order;
        for (int i = 0; i < requestForBooks.size(); i++) {
            if(requestForBooks.get(i).getRequestStatus() == RequestForBookStatus.ACTIVE){
                order = requestForBooks.get(i).getOrder();
                requestForBooksLocal = order.getListOfRequestForBooks();
                for (int j = 0; j < requestForBooksLocal.size(); j++) {
                    if(requestForBooksLocal.get(j).getBook().getId() == book.getId()){
                        requestForBooks.get(i).setRequestStatus(RequestForBookStatus.COMPLETED);
                        requestForBooksLocal.remove(requestForBooksLocal.get(j));
                        if(requestForBooksLocal.size() == 0) {
                            orderService.executeOrder(order);
                        }
                    }
                }
            }

        }
    }

    public void showUnsoldBooksMoreThanSixMonth() {
        LocalDate date = LocalDate.now();
        List<Book> books = bookRepository.getAll();
        List<Book> arrayOfUnsoldBooksMoreThanSixMonth = new ArrayList<>();
        System.out.println("Books unsold for more than 6 month : ");
        for (int i = 0; i < books.size(); i++) {

            if(books.get(i).getBookStatus() == BookStatus.IN_STOCK) {
                LocalDate date2 = books.get(i).getArriveDate().plusMonths(6);
                int compareResult = date2.compareTo(date);
                if (compareResult < 0) {
                    arrayOfUnsoldBooksMoreThanSixMonth.add(books.get(i));
                }
            }
        }
        for (int i = 0; i < arrayOfUnsoldBooksMoreThanSixMonth.size(); i++) {
            System.out.println(arrayOfUnsoldBooksMoreThanSixMonth.get(i).getTitle());
        }
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public void showBooksInStock(){
        List<Book> books = bookRepository.getAll();
        System.out.println("List of the books in stock");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " " + i);
        }

    }

    public void sortBookByPrice() {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookPriceComparator);
        System.out.println("List of books sorted by price: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPrice());
        }
    }

    public void sortBookByAuthor() {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAlphabeticalComparator);
        System.out.println("List of books sorted by author: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " +books.get(i).getAuthor());
        }
    }

    public void sortBookByDateArrive() {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookDataComparator);
        System.out.println("List of books sorted by date of arrive: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }
    public void sortBookByAvailabilityInStock() {
        BookAvailabilityComparator bookAvailabilityComparator = new BookAvailabilityComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by availability in stock: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + "-" + books.get(i).getBookStatus());
        }
    }

    public void sortBookByPublicationDate() {
        BookPublicationDataComparator bookAvailabilityComparator = new BookPublicationDataComparator();
        List<Book> books = bookRepository.getAll();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by date of publication: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }

    public Book getBookById(int id){
        Book book = bookRepository.read(id);
        return book;
    }

    public void bookSort() {
        sortBookByPrice();
        sortBookByAuthor();
        sortBookByDateArrive();
        sortBookByAvailabilityInStock();
        sortBookByPublicationDate();
    }

}
