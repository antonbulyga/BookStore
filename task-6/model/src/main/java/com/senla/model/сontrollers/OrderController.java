package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.service.OrderService;

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

    public Order createOrder(List<Book> books, Customer customer, LocalDate dateOfDoneOrder){
      Order order =  OrderService.getInstance().createOrder(books, customer, dateOfDoneOrder);
      return order;
    }

    public Order getOrderById(int id){
        Order order = OrderService.getInstance().getOrderById(id);
        return order;
    }

    public void updateOrder(Order order) {
        OrderService.getInstance().updateOrder(order);
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
