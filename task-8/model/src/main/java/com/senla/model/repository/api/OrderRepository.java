package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    void addOrderToListOfOrders(Order order);

    Order createOrder(List<Book> books , Customer customer, LocalDate dateOfDoneOrder);

    void updateOrder(Order order);

    void deleteOrder(Order order);

    Order getOrderById(int id);

    void setListOfOrders(List<Order> listOfOrders);

    List<Order> getListOfOrders();

}
