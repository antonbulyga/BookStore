package com.senla.bookstore.main.model.сomparators;

import com.senla.bookstore.main.model.entity.RequestForBook;

import java.util.Comparator;

public class RequestForBookAlphabeticalComparator implements Comparator<RequestForBook> {
    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return CharSequence.compare(a.getBook().getTitle(), b.getBook().getTitle());
    }
}
