package com.senla.bookstore.model.Comparators;

import com.senla.bookstore.model.Book;

import java.util.Comparator;

public class BookPublicationDataComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getPublicationDate().compareTo(b.getPublicationDate());
    }
}
