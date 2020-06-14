package com.senla.bookstore.model.Comparators;

import com.senla.bookstore.model.Order;

import java.util.Comparator;

public class OrderPriceComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        return Double.compare(a.getPriceOfOrder(), b.getPriceOfOrder());
    }
}
