package com.senla.bookstore.main.model.сomparators;

import com.senla.bookstore.main.model.entity.Order;

import java.util.Comparator;

public class OrderPriceComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        return Double.compare(a.getPriceOfOrder(), b.getPriceOfOrder());
    }
}
