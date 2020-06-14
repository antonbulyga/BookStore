package com.senla.bookstore.model.Comparators;

import com.senla.bookstore.model.Book;

import java.util.Comparator;

public class BookAlphabeticalComparator implements Comparator <Book> {

    @Override
    public int compare(Book a, Book b) {
        return CharSequence.compare(a.getAuthor(), b.getAuthor());
    }
}
