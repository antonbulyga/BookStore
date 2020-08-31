package com.senla.model.сomparators;

import com.senla.model.entity.RequestForBook;

import java.util.Comparator;

public class RequestForBookAlphabeticalComparator implements Comparator<RequestForBook> {
    @Override
    public int compare(RequestForBook a, RequestForBook b) {
        return CharSequence.compare(a.getTitleOfBook(), b.getTitleOfBook());
    }
}
