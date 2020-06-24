package com.senla.bookstore.main.model.сomparators;

import com.senla.bookstore.main.model.entity.Order;

import java.util.Comparator;

public class OrderStatusComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        return a.getOrderStatus().compareTo(b.getOrderStatus());
    }

}
