package main.java.com.senla.model.сomparators;

import main.java.com.senla.model.entity.Order;

import java.util.Comparator;

public class OrderStatusComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        return a.getOrderStatus().compareTo(b.getOrderStatus());
    }

}
