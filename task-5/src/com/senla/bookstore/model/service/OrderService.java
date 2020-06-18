package com.senla.bookstore.model.service;

import com.senla.bookstore.model.сomparators.OrderDataOfDoneComparator;
import com.senla.bookstore.model.сomparators.OrderPriceComparator;
import com.senla.bookstore.model.сomparators.OrderStatusComparator;
import com.senla.bookstore.model.сomparators.RequestForBookStatus;
import com.senla.bookstore.model.entity.*;
import com.senla.bookstore.model.enumeration.OrderStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private Store store;

    private OrderService() {
        store = StoreService.getInstance().getStore();
    }

    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    public List<Order> getListOfOrders() {
        List<Order> orders = store.getListOfOrders();
        return orders;
    }

    public void addOrderToListOfOrders(Order order){
        List<Order> orders = store.getListOfOrders();
        orders.add(order);
        store.setListOfOrders(orders);
    }

    public Order createOrder(List<Book> books , Customer customer){
        LocalDate date = LocalDate.now();
        int priceOfOrder = 0;
        Order order = new Order(1, date , date, books, OrderStatus.NEW, customer, 0);
        for (int i = 0; i < books.size(); i++) {
           priceOfOrder += books.get(i).getPrice();
        }
        order.setPriceOfOrder(priceOfOrder);
        return order;
    }

    public void showListOfOrders(){
        List<Order> orders = store.getListOfOrders();
        System.out.println("List of orders: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + i);
        }
    }


    public void sortOrdersByDateOfDone() {
        OrderDataOfDoneComparator orderDataOfDoneComparator = new OrderDataOfDoneComparator();
        List<Order> orders = store.getListOfOrders();
        Collections.sort(orders, orderDataOfDoneComparator);
        System.out.println("List of orders sorted by date of done: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getDateOfDoneOrder());
        }
    }

    public void sortOrdersByPrice() {
        OrderPriceComparator orderPriceComparator = new OrderPriceComparator();
        List<Order> orders = store.getListOfOrders();
        Collections.sort(orders, orderPriceComparator);
        System.out.println("List of orders sorted by price: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " +orders.get(i).getPriceOfOrder());
        }
    }

    public void sortOrdersByStatus() {
        OrderStatusComparator orderStatusComparator = new OrderStatusComparator();
        List<Order> orders = store.getListOfOrders();
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

    public void deleteOrder(Order order){
        List<RequestForBook> arrayOfRequestBooks = store.getListOfRequestBooks();
        List<RequestForBook> requestForBooksLocal = order.getArrayOfRequestForBooks();
        List<Order> arrayOfOrders = store.getListOfOrders();
        for (int i = 0; i < arrayOfRequestBooks.size(); i++) {
            for (int j = 0; j < requestForBooksLocal.size(); j++) {
                if(arrayOfRequestBooks.get(i) == requestForBooksLocal.get(j)){
                    arrayOfRequestBooks.remove(arrayOfRequestBooks.get(i));
                }
            }
        }
        for (int i = 0; i < arrayOfOrders.size(); i++) {
            if(order.getId() == arrayOfOrders.get(i).getId()){
                arrayOfOrders.remove(arrayOfOrders.get(i));
            }
        }
        store.setListOfRequestBooks(arrayOfRequestBooks);
        store.setListOfOrders(arrayOfOrders);
    }

    public void changeOrderStatusToCancelled(Order order){
        order.setOrderStatus(OrderStatus.CANCELLED);
        List<RequestForBook> requestForBooks = order.getArrayOfRequestForBooks();
        for (int i = 0; i < requestForBooks.size(); i++) {
            requestForBooks.get(i).setRequestStatus(RequestForBookStatus.CANCELLED);
        }
    }

    public void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2) {
        int sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) == 0) {
                sum++;
            }
        }
        System.out.println("Count of orders by period of time " + " from " + date1 + " to " + date2 + " is " + sum);
    }


    public void orderSort() {
        sortOrdersByDateOfDone();
        sortOrdersByPrice();
        sortOrdersByStatus();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
