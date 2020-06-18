package com.senla.bookstore.model.сomparators;

import com.senla.bookstore.model.entity.Book;

import java.util.Comparator;

public class BookAlphabeticalComparator implements Comparator <Book> {

    @Override
    public int compare(Book a, Book b) {
        return CharSequence.compare(a.getAuthor(), b.getAuthor());
    }
}
