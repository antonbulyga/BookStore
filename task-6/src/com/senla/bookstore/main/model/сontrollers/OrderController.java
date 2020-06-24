package com.senla.bookstore.main.model.сontrollers;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.service.OrderService;

import java.time.LocalDate;
import java.util.List;

public class OrderController {
    private static OrderController instance;

    private OrderController(){

    }

    public static OrderController getInstance(){
        if(instance == null){
            instance = new OrderController();
        }
        return instance;
    }

    public List<Order> getListOfOrders() {
        List<Order> orders = OrderService.getInstance().getListOfOrders();
        return orders;
    }

    public Order createOrder(List<Book> books, Customer customer, String dateOfDoneOrderString){
      Order order =  OrderService.getInstance().createOrder(books, customer, dateOfDoneOrderString);
      return order;
    }

    public void addOrderToListOfOrders(Order order){
        OrderService.getInstance().addOrderToListOfOrders(order);
    }

    public void showListOfOrders(){
        OrderService.getInstance().showListOfOrders();
    }

    public void sortOrdersByDateOfDone(){OrderService.getInstance().sortOrdersByDateOfDone();
    }

    public void sortOrdersByPrice(){
        OrderService.getInstance().sortOrdersByPrice();
    }

    public void sortOrdersByStatus(){
        OrderService.getInstance().sortOrdersByStatus();
    }

    public void showDetailsOfOrder(Order order){
        OrderService.getInstance().showDetailsOfOrder(order);
    }

    public void deleteOrder(Order order){
        OrderService.getInstance().deleteOrder(order);
    }

    public void changeOrderStatusToCancelled(Order order){
        OrderService.getInstance().changeOrderStatusToCancelled(order);
    }

    public  void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2){
        OrderService.getInstance().countOfDoneOrdersByPeriodOfTime(orders, date1, date2);
    }


}
