package main.java.com.senla.model.service;

import annotation.Config;
import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.repository.BookRepository;
import main.java.com.senla.model.repository.OrderRepository;
import main.java.com.senla.model.repository.RequestForBookRepository;
import main.java.com.senla.model.repository.StockLevelRepository;
import main.java.com.senla.model.service.api.BookService;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.model.utils.generators.StockLevelIdGenerator;
import main.java.com.senla.model.сomparators.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;

    private BookServiceImpl() {

    }

    public static BookServiceImpl getInstance(){
        if(instance == null){
            instance = new BookServiceImpl();
        }
        return instance;
    }

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
       Book book = BookRepository.getInstance().createBook(id, title, author, price, publicationDate);
        RequestForBookServiceImpl.getInstance().closerRequestForBooksAfterArrivingBook(book);
        //BookService.getInstance().arriveBookToStock(book);
        //BookService.getInstance().completingRequestAfterArrivingNewBook(book);
       return book;
    }
    public List<Book> getListOfBooksInStoreHouse(){
       List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
       return books;
    }

    public void addBookToListOfBookInTheStorehouse(Book book){
        BookRepository.getInstance().addBookToListOfBookInTheStorehouse(book);
    }

    public void bookUpdate(Book book) {
        BookRepository.getInstance().bookUpdate(book);
    }

    public List<Book> setStaleBookStatus(){
        int countOfMonthToMarkBookAsStale = getCountOfMonthToMarkBookAsStale();
        List<Book> books = BookRepository.getInstance().getListOfBooksInStorehouse();
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
        @Config(key = "maxCountOfMonth")
        String maxCountOfMonthString = null;
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
                            OrderServiceImpl.getInstance().executeOrder(order);
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
        List<StockLevel> stockLevels = StockLevelRepository.getInstance().getListOfStockLevels();
        if(stockLevels.size() == 0){
            StockLevel stockLevel = new StockLevel(StockLevelIdGenerator.getStockLevelId(), book, 0);
            stockLevels.add(stockLevel);
            StockLevelRepository.getInstance().setListOfStockLevels(stockLevels);
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
            StockLevelRepository.getInstance().setListOfStockLevels(stockLevels);
        }
    }

    public void setListOfBooksInStoreHouse(List<Book> books){
        BookRepository.getInstance().setListOfBooksInStorehouse(books);
    }

    public void deleteBook(Book book){
        BookRepository.getInstance().deleteBook(book);
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
        Book book = BookRepository.getInstance().getBookById(id);
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
