package com.senla.model.service.api;

import com.senla.model.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void addOrderToListOfOrders(Order order);
    Order createOrder(List<Book> books, List<RequestForBook> requestForBooks, Customer customer, LocalDate dateOfDoneOrder);
    void updateOrder(Order order);
    void deleteOrder(Order order);
    Order getOrderById(int id);
    List<Order> getListOfOrders();
    void executeOrder(Order order);
    void sumOfMoneyPerPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2);
    void showListOfOrders();
    void sortOrdersByDateOfDone();
    void sortOrdersByPrice();
    void sortOrdersByStatus();
    void showDetailsOfOrder(Order order);
    void changeOrderStatusToCancelled(Order order);
    void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2);
    void importOrder();
    void exportOrder();
    Order read(Integer orderId);
}
