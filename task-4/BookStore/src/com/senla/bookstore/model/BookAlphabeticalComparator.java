package com.senla.bookstore.model;

import java.util.Comparator;

public class BookAlphabeticalComparator implements Comparator<Book> {

    @Override
    public int compare(Book a, Book b) {
        return CharSequence.compare(a.getAuthor(), b.getAuthor());
    }
}
