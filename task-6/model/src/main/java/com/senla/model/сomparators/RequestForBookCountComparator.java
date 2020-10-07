package main.java.com.senla.model.сomparators;

import main.java.com.senla.model.entity.RequestForBook;

import java.util.Comparator;

public class RequestForBookCountComparator implements Comparator<RequestForBook> {

    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return Integer.compare(a.getBook().getRequestForBooks().size(), b.getBook().getRequestForBooks().size());
    }

}
