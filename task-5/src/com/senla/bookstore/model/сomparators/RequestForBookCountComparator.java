package com.senla.bookstore.model.сomparators;

import com.senla.bookstore.model.entity.RequestForBook;

import java.util.Comparator;

public class RequestForBookCountComparator implements Comparator<RequestForBook> {

    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return Integer.compare(a.getBook().getRequestForBooks().size(), b.getBook().getRequestForBooks().size());
    }

}
