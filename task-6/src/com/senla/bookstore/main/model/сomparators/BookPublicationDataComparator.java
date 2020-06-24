package com.senla.bookstore.main.model.сomparators;

import com.senla.bookstore.main.model.entity.Book;

import java.util.Comparator;

public class BookPublicationDataComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getPublicationDate().compareTo(b.getPublicationDate());
    }
}
