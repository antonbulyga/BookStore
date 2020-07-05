package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.service.OrderServiceImpl;

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

    public void sumOfMoneyPerPeriodOfTime(List<Order> orders , LocalDate date1, LocalDate date2) {
        OrderServiceImpl.getInstance().sumOfMoneyPerPeriodOfTime(orders, date1, date2);
    }

    public void addOrderToStore(Order order){
        OrderServiceImpl.getInstance().addOrderToStore(order);
    }

    public void executeOrder(Order order){
        OrderServiceImpl.getInstance().executeOrder(order);
    }


    public List<Order> getListOfOrders() {
        List<Order> orders = OrderServiceImpl.getInstance().getListOfOrders();
        return orders;
    }

    public void setListOfOrders(List<Order> orders){
        OrderServiceImpl.getInstance().setListOfOrders(orders);
    }

    public Order createOrder(List<Book> books, Customer customer, LocalDate dateOfDoneOrder){
      Order order =  OrderServiceImpl.getInstance().createOrder(books, customer, dateOfDoneOrder);
      return order;
    }

    public Order getOrderById(int id){
        Order order = OrderServiceImpl.getInstance().getOrderById(id);
        return order;
    }

    public void updateOrder(Order order) {
        OrderServiceImpl.getInstance().updateOrder(order);
    }

    public void addOrderToListOfOrders(Order order){
        OrderServiceImpl.getInstance().addOrderToListOfOrders(order);
    }

    public void showListOfOrders(){
        OrderServiceImpl.getInstance().showListOfOrders();
    }

    public void sortOrdersByDateOfDone(){
        OrderServiceImpl.getInstance().sortOrdersByDateOfDone();
    }

    public void sortOrdersByPrice(){
        OrderServiceImpl.getInstance().sortOrdersByPrice();
    }

    public void sortOrdersByStatus(){
        OrderServiceImpl.getInstance().sortOrdersByStatus();
    }

    public void showDetailsOfOrder(Order order){
        OrderServiceImpl.getInstance().showDetailsOfOrder(order);
    }

    public void deleteOrder(Order order){
        OrderServiceImpl.getInstance().deleteOrder(order);
    }

    public void changeOrderStatusToCancelled(Order order){
        OrderServiceImpl.getInstance().changeOrderStatusToCancelled(order);
    }

    public  void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2){
        OrderServiceImpl.getInstance().countOfDoneOrdersByPeriodOfTime(orders, date1, date2);
    }


}
