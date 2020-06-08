package com.senla.bookstore.model;

import java.util.Comparator;

public class OrderDataOfDoneComparator implements Comparator<Order> {

    @Override
    public int compare(Order a, Order b) {
        if (a.getDateOfDoneOrder() == null && b.getDateOfDoneOrder() != null)
            return -1;
        else if(a.getDateOfDoneOrder() != null && b.getDateOfDoneOrder() == null)
            return 1;
        else if (a.getDateOfDoneOrder() == null && b.getDateOfDoneOrder() == null)
            return 0;


       else return a.getDateOfDoneOrder().compareTo(b.getDateOfDoneOrder());
    }
}

