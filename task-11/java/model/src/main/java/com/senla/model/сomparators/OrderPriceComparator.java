package com.senla.model.сomparators;

import com.senla.model.entity.Order;

import java.util.Comparator;

public class OrderPriceComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        return Double.compare(a.getPriceOfOrder(), b.getPriceOfOrder());
    }
}
