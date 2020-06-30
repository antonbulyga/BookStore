package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.repository.BookRepository;
import main.java.com.senla.model.repository.OrderRepository;
import main.java.com.senla.model.repository.RequestForBookRepository;
import main.java.com.senla.model.repository.StockRepository;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.model.utils.generators.StockLevelIdGenerator;
import main.java.com.senla.model.сomparators.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookService {
    private static BookService instance;

    private BookService() {

    }

    public static BookService getInstance(){
        if(instance == null){
            instance = new BookService();
        }
        return instance;
    }

    public void setStaleBookStatus(){
        int countOfMonthToMarkBookAsStale = getCountOfMonthToMarkBookAsStale();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        LocalDate arriveDate = null;
        LocalDate nowDate = arriveDate.now();
        for (int i = 0; i < books.size(); i++) {
            arriveDate = books.get(i).getArriveDate();
            long countOfMonth = ChronoUnit.MONTHS.between(arriveDate, nowDate);
            if(countOfMonth > countOfMonthToMarkBookAsStale){
                books.get(i).setBookStatus(BookStatus.STALE);
            }
        }
    }

    public  int getCountOfMonthToMarkBookAsStale(){
        int countOfMonthToMarkBookAsStale = 0;
        String countOfMonthToMarkBookAsStaleString = PropertyData.getProperty("maxCountOfMonth");
        try {
            countOfMonthToMarkBookAsStale = Integer.parseInt(countOfMonthToMarkBookAsStaleString);
        }
        catch (NumberFormatException e){
            System.out.println("The data in property is incorrect");
        }
        return countOfMonthToMarkBookAsStale;
    }

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
        List<RequestForBook> requestForBooks = new ArrayList<>();
        LocalDate arriveDate = LocalDate.now();
        Book book = new Book(id, title, author, price, BookStatus.IN_STOCK, requestForBooks, arriveDate, publicationDate);
        addBookToListOfBookInTheStorehouse(book);
        BookService.getInstance().arriveBookToStock(book);
        BookService.getInstance().completingRequestAfterArrivingNewBook(book);
        return book;
    }

    public void completingRequestAfterArrivingNewBook(Book book) {
        List<RequestForBook> requestForBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        List<RequestForBook> requestForBooksLocal;
        Order order;
        List<Order> arraysOfOrders = OrderRepository.getInstance().getListOfOrders();
        for (int i = 0; i < requestForBooks.size(); i++) {
            if(requestForBooks.get(i).getRequestStatus() == RequestForBookStatus.ACTIVE){
                order = requestForBooks.get(i).getOrder();
                requestForBooksLocal = order.getArrayOfRequestForBooks();
                for (int j = 0; j < requestForBooksLocal.size(); j++) {
                    if(requestForBooksLocal.get(j).getBook().getId() == book.getId()){
                        requestForBooks.get(i).setRequestStatus(RequestForBookStatus.COMPLETED);
                        requestForBooksLocal.remove(requestForBooksLocal.get(j));
                        OrderRepository.getInstance().setListOfOrders(arraysOfOrders);
                        if(requestForBooksLocal.size() == 0) {
                            OrderService.getInstance().executeOrder(order);
                        }
                    }
                }
            }

        }
    }

    public void showUnsoldBooksMoreThanSixMonth() {
        LocalDate date = LocalDate.now();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
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

    public void arriveBookToStock(Book book) {
        int countOfBooksInStock;
        List<StockLevel> stockLevels = StockRepository.getInstance().getListOfStockLevels();
        if(stockLevels.size() == 0){
            StockLevel stockLevel = new StockLevel(StockLevelIdGenerator.getStockLevelId(), book, 0);
            stockLevels.add(stockLevel);
            StockRepository.getInstance().setListOfStockLevels(stockLevels);
        }
        else {
            for (int i = 0; i < stockLevels.size(); i++) {
                if (stockLevels.get(i).getBook().getId() == book.getId()) {
                    stockLevels.get(i).getBook().setBookStatus(BookStatus.IN_STOCK);
                    countOfBooksInStock = stockLevels.get(i).getCount();
                    countOfBooksInStock = countOfBooksInStock + 1;
                    stockLevels.get(i).setCount(countOfBooksInStock);
                }
            }
            StockRepository.getInstance().setListOfStockLevels(stockLevels);
        }
    }

    public void addBookToListOfBookInTheStorehouse(Book book){
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        books.add(book);
        BookRepository.getInstance().setListOfBooksInStorehouse(books);
    }

    public List<Book> getListOfBooksInStoreHouse(){
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        return books;
    }

    public void addBookToListOfBooks(Book book){
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        books.add(book);
        BookRepository.getInstance().setListOfBooksInStorehouse(books);
    }

    public void showBooksInStock(){
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        System.out.println("List of the books in stock");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " " + i);
        }

    }

    public void sortBookByPrice() {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        Collections.sort(books, bookPriceComparator);
        System.out.println("List of books sorted by price: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPrice());
        }
    }

    public void sortBookByAuthor() {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        Collections.sort(books, bookAlphabeticalComparator);
        System.out.println("List of books sorted by author: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " +books.get(i).getAuthor());
        }
    }

    public void sortBookByDateArrive() {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        Collections.sort(books, bookDataComparator);
        System.out.println("List of books sorted by date of arrive: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }
    public void sortBookByAvailabilityInStock() {
        BookAvailabilityComparator bookAvailabilityComparator = new BookAvailabilityComparator();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by availability in stock: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + "-" + books.get(i).getBookStatus());
        }
    }

    public void sortBookByPublicationDate() {
        BookPublicationDataComparator bookAvailabilityComparator = new BookPublicationDataComparator();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by date of publication: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }

    public Book getBookById(int id){
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
        Book book = null;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id){
               book = books.get(i);
            }
        }
        return book;
    }

    public void bookUpdate(Book book) {
        List<StockLevel> stockLevels = StockRepository.getInstance().getListOfStockLevels();

        for (int i = 0; i < stockLevels.size(); i++) {
            if (stockLevels.get(i).getBook().getId() == book.getId()) {
                BookService.getInstance().arriveBookToStock(book);
            }
        }
    }

    public void bookSort() {
        sortBookByPrice();
        sortBookByAuthor();
        sortBookByDateArrive();
        sortBookByAvailabilityInStock();
        sortBookByPublicationDate();
    }

}
