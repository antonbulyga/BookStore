package com.senla.bookstore.model;

import java.util.Comparator;

public class RequestForBookCountComparator implements Comparator<RequestForBook> {

    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return Integer.compare(a.getBook().getRequestForBooks().length, b.getBook().getRequestForBooks().length);
    }

}
