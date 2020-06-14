package com.senla.bookstore.service;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Comparators.*;
import com.senla.bookstore.model.Store;


import java.util.Collections;
import java.util.List;

public class BookService {
    public void showBooksInStock(List<Book> books){
        System.out.println("List of the books in stock");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }

    public void sortBookByPrice(List<Book> books) {
        BookPriceComparator bookPriceComparator = new BookPriceComparator();
        Collections.sort(books, bookPriceComparator);
        System.out.println("List of books sorted by price: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getPrice());
        }
    }

    public void sortBookByAuthor(List<Book> books) {
        BookAlphabeticalComparator bookAlphabeticalComparator = new BookAlphabeticalComparator();
        Collections.sort(books, bookAlphabeticalComparator);
        System.out.println("List of books sorted by autor: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " +books.get(i).getAuthor());
        }
    }

    public void sortBookByDateArrive(List<Book> books) {
        BookArriveDataComparator bookDataComparator = new BookArriveDataComparator();
        Collections.sort(books, bookDataComparator);
        System.out.println("List of books sorted by date of arrive: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " +books.get(i).getArriveDate());
        }
    }
    public void sortBookByAvailabilityInStock(List<Book> books) {
        BookAvailabilityComparator bookAvailabilityComparator = new BookAvailabilityComparator();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by availability in stock: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + "-" + books.get(i).getBookStatus());
        }
    }

    public void sortBookByPublicationDate(List<Book> books) {
        BookPublicationDataComparator bookAvailabilityComparator = new BookPublicationDataComparator();
        Collections.sort(books, bookAvailabilityComparator);
        System.out.println("List of books sorted by date of publication: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " - " + books.get(i).getArriveDate());
        }
    }

    public void bookSort(Store store) {
        List<Book> books = store.getListOfBooksInStorehouse();
        sortBookByPrice(books);
        sortBookByAuthor(books);
        sortBookByDateArrive(books);
        sortBookByAvailabilityInStock(books);
        sortBookByPublicationDate(books);
    }
}
