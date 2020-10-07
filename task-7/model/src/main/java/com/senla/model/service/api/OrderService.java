package main.java.com.senla.model.service.api;

import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.enumeration.OrderStatus;
import main.java.com.senla.model.repository.StockLevelRepositoryImpl;
import main.java.com.senla.model.utils.generators.OrderIdGenerator;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void addOrderToListOfOrders(Order order);
    Order createOrder(List<Book> books , Customer customer, LocalDate dateOfDoneOrder);
    void updateOrder(Order order);
    void deleteOrder(Order order);
    Order getOrderById(int id);
    void setListOfOrders(List<Order> listOfOrders);
    List<Order> getListOfOrders();
    void executeOrder(Order order);
    void sumOfMoneyPerPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2);
    void addOrderToStore(Order order);
    void showListOfOrders();
    void sortOrdersByDateOfDone();
    void sortOrdersByPrice();
    void sortOrdersByStatus();
    void showDetailsOfOrder(Order order);
    void changeOrderStatusToCancelled(Order order);
    void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2);
    void importOrder();
    void exportOrder();
}
