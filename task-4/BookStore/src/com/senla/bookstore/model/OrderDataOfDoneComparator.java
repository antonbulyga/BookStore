package com.senla.bookstore.model;

import java.util.Comparator;

public class OrderDataOfDoneComparator implements Comparator<Order> {

    @Override
    public int compare(Order a, Order b) {
        return a.getDateOfDoneOrder().compareTo(b.getDateOfDoneOrder());
    }
}
