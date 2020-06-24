package com.senla.bookstore.main.model.service;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.RequestForBook;
import com.senla.bookstore.main.model.entity.StockLevel;
import com.senla.bookstore.main.model.enumeration.BookStatus;
import com.senla.bookstore.main.model.сomparators.*;
import com.senla.bookstore.main.model.entity.Store;

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
    public Book createBook(int id, String title, String author, double price, String bookStatusString, String publicationDate){
        List<RequestForBook> requestForBooks = new ArrayList<>();
        BookStatus bookStatus = null;

        if(bookStatusString.equals("IN_STOCK")){
            bookStatus = BookStatus.IN_STOCK;
        }
        else {
            bookStatus = BookStatus.OUT_OF_STOCK;
        }
        int yearOfPublication = Integer.parseInt(publicationDate.substring(0,4));
        int monthOfPublication = Integer.parseInt(publicationDate.substring(4,6));
        int dayOfPublication = Integer.parseInt(publicationDate.substring(6,8));
        LocalDate dateOfPublication = LocalDate.of(yearOfPublication, monthOfPublication, dayOfPublication);
        LocalDate arriveDate = LocalDate.now();

        Book book = new Book(id, title, author, price, bookStatus, requestForBooks, arriveDate, dateOfPublication);
        List<Book> books = store.getListOfBooksInStorehouse();
        int counter = 0;
        if(books.size() == 0){
            books.add(book);
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId() != book.getId()) {
                    counter++;
                }
            }
            if (counter == books.size()) {
                books.add(book);
            }
                /*else {
                    List<StockLevel> levels = store.getStock().getArrayOfStockLevels();
                    for (int j = 0; j < levels.size(); j++) {
                        if(levels.get(j).equals(book)){
                            countOfBookInStock = store.getStock().getArrayOfStockLevels().get(j).getCount();
                            countOfBookInStock++;
                            store.getStock().getArrayOfStockLevels().get(j).setCount(countOfBookInStock);
                        }

                    }
                }*/

        }
        store.setListOfBooksInStorehouse(books);
        List<StockLevel> stockLevels = store.getStock().getArrayOfStockLevels();
        if(stockLevels.size() == 0) {
            StockLevel stockLevel = new StockLevel(book, 0);
            stockLevels.add(stockLevel);
            StockLevelService.getInstance().setArrayOfStockLevels(stockLevels);
            StoreService.getInstance().arriveBookToStock(book);
            StoreService.getInstance().completingRequestAfterArrivingNewBook(book);
        }
        else {
            for (int i = 0; i < stockLevels.size(); i++) {
                if(stockLevels.get(i).getBook().getId() == book.getId()){
                    StoreService.getInstance().arriveBookToStock(book);
                    StoreService.getInstance().completingRequestAfterArrivingNewBook(book);
                    break;
                }
                else {
                    StockLevel stockLevel = new StockLevel(book, 0);
                    stockLevels.add(stockLevel);
                    StockLevelService.getInstance().setArrayOfStockLevels(stockLevels);
                    StoreService.getInstance().arriveBookToStock(book);
                    break;
                }
            }
        }
        return book;
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
