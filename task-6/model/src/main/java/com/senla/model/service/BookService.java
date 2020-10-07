package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.сomparators.*;
import main.java.com.senla.model.entity.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookService {
    private  static BookService instance;
    private Store store;

    private BookService() {
        store = StoreService.getInstance().getStore();
    }

    public static BookService getInstance(){
        if(instance == null){
            instance = new BookService();
        }
        return instance;
    }
    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
        List<RequestForBook> requestForBooks = new ArrayList<>();
        LocalDate arriveDate = LocalDate.now();
        Book book = new Book(id, title, author, price, BookStatus.IN_STOCK, requestForBooks, arriveDate, publicationDate);
        addBookToListOfBookInTheStorehouse(book);
        StoreService.getInstance().arriveBookToStock(book);
        StoreService.getInstance().completingRequestAfterArrivingNewBook(book);
        return book;
    }

    public void addBookToListOfBookInTheStorehouse(Book book){
        List<Book> books = store.getListOfBooksInStorehouse();
        books.add(book);
        store.setListOfBooksInStorehouse(books);
    }

    public List<Book> getListOfBooksInStoreHouse(){
        List<Book> books = store.getListOfBooksInStorehouse();
        return books;
    }

    public void addBookToListOfBooks(Book book){
        List<Book> books = store.getListOfBooksInStorehouse();
        books.add(book);
        store.setListOfBooksInStorehouse(books);
    }

    public void showBooksInStock(){
        List<Book> books = store.getListOfBooksInStorehouse();
        System.out.println("List of the books in stock");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " " + i);
        }

    }

    public void sortBookByPrice() {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        List<Book> books = store.getListOfBooksInStorehouse();
        Collections.sort(books, bookPriceComparator);
        System.out.println("List of books sorted by price: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPrice());
        }
    }

    public void sortBookByAuthor() {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        List<Book> books = store.getListOfBooksInStorehouse();
        Collections.sort(books, bookAlphabeticalComparator);
        System.out.println("List of books sorted by autor: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " +books.get(i).getAuthor());
        }
    }

    public void sortBookByDateArrive() {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        List<Book> books = store.getListOfBooksInStorehouse();
        Collections.sort(books, bookDataComparator);
        System.out.println("List of books sorted by date of arrive: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }
    public void sortBookByAvailabilityInStock() {
        BookAvailabilityComparator bookAvailabilityComparator = new BookAvailabilityComparator();
        List<Book> books = store.getListOfBooksInStorehouse();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by availability in stock: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + "-" + books.get(i).getBookStatus());
        }
    }

    public void sortBookByPublicationDate() {
        BookPublicationDataComparator bookAvailabilityComparator = new BookPublicationDataComparator();
        List<Book> books = store.getListOfBooksInStorehouse();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by date of publication: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }

    public Book getBookById(int id){
        List<Book> books = store.getListOfBooksInStorehouse();
        Book book = null;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id){
               book = books.get(i);
            }
        }
        return book;
    }

    public void bookUpdate(Book book) {
        List<StockLevel> stockLevels = store.getStock().getArrayOfStockLevels();

        for (int i = 0; i < stockLevels.size(); i++) {
            if (stockLevels.get(i).getBook().getId() == book.getId()) {
                StoreService.getInstance().arriveBookToStock(book);
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
