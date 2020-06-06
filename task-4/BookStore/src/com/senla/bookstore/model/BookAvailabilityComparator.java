package com.senla.bookstore.model;

import java.util.Comparator;

public class BookAvailabilityComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getBookStatus().compareTo(b.getBookStatus());
    }

}
