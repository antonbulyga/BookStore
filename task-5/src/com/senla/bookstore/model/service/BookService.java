package com.senla.bookstore.model.service;

import com.senla.bookstore.model.entity.Book;
import com.senla.bookstore.model.сomparators.*;
import com.senla.bookstore.model.entity.Store;

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
