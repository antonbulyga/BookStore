package com.senla.bookstore.model;

import java.util.Comparator;


public class BookArriveDataComparator implements Comparator<Book> {

    @Override
    public int compare(Book a, Book b) {
        return a.getArriveDate().compareTo(b.getArriveDate());
    }

}
