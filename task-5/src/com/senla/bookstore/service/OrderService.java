package com.senla.bookstore.service;

import com.senla.bookstore.model.*;
import com.senla.bookstore.model.Comparators.OrderDataOfDoneComparator;
import com.senla.bookstore.model.Comparators.OrderPriceComparator;
import com.senla.bookstore.model.Comparators.OrderStatusComparator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class OrderService {

   public Order createOrder(List<Book> books, Customer customer){
       LocalDate date = LocalDate.now();
        int priceOfOrder = 0;
        Order order = new Order(1, date , null, books, OrderStatus.NEW, customer, 0);
        for (int i = 0; i < books.size(); i++) {
           priceOfOrder += books.get(i).getPrice();
        }
        order.setPriceOfOrder(priceOfOrder);
        return order;
    }

    public void showListOfOrders(List<Order> orders){
        System.out.println("List of orders: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }


    public void sortOrdersByDateOfDone(List<Order> orders) {
        OrderDataOfDoneComparator orderDataOfDoneComparator = new OrderDataOfDoneComparator();
        Collections.sort(orders, orderDataOfDoneComparator);
        System.out.println("List of orders sorted by date of done: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getDateOfDoneOrder());
        }
    }

    public void sortOrdersByPrice(List<Order> orders) {
        OrderPriceComparator orderPriceComparator = new OrderPriceComparator();
        Collections.sort(orders, orderPriceComparator);
        System.out.println("List of orders sorted by price: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " +orders.get(i).getPriceOfOrder());
        }
    }

    public void sortOrdersByStatus(List<Order> orders) {
        OrderStatusComparator orderStatusComparator = new OrderStatusComparator();
        Collections.sort(orders, orderStatusComparator);
        System.out.println("List of orders sorted by status: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " +orders.get(i).getOrderStatus());
        }
    }

    public void showDetailsOfOrder(Order order){
        System.out.println("Details of the customer: " + "name: " + order.getCustomer().getName() + " age " + order.getCustomer().getAge());
        System.out.println("Details of the order : " + "price of order is " +  order.getPriceOfOrder() + ", date of done order is: " +order.getDateOfDoneOrder());
    }

    public void orderSort(Store store) {
        sortOrdersByDateOfDone(store.getListOfOrders());
        sortOrdersByPrice(store.getListOfOrders());
        sortOrdersByStatus(store.getListOfOrders());
    }

}
