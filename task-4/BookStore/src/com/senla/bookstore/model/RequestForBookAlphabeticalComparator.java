package com.senla.bookstore.model;

import java.util.Comparator;

public class RequestForBookAlphabeticalComparator implements Comparator<RequestForBook> {
    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return CharSequence.compare(a.getBook().getTitle(), b.getBook().getTitle());
    }
}
