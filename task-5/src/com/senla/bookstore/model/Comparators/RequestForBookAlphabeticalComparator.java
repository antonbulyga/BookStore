package com.senla.bookstore.model.Comparators;

import com.senla.bookstore.model.RequestForBook;

import java.util.Comparator;

public class RequestForBookAlphabeticalComparator implements Comparator<RequestForBook> {
    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return CharSequence.compare(a.getBook().getTitle(), b.getBook().getTitle());
    }
}
