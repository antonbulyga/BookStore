package main.java.com.senla.model.сontrollers;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.service.api.OrderService;

import java.time.LocalDate;
import java.util.List;

public class OrderController {
    private static OrderController instance;
    @MyAutoWired
    private OrderService orderService;

    private OrderController(){

    }

    public static OrderController getInstance(){
        if(instance == null){
            instance = new OrderController();
        }
        return instance;
    }

    public void importOrder(){
        orderService.importOrder();
    }

    public void exportOrder(){
        orderService.exportOrder();
    }

    public void sumOfMoneyPerPeriodOfTime(List<Order> orders , LocalDate date1, LocalDate date2) {
        orderService.sumOfMoneyPerPeriodOfTime(orders, date1, date2);
    }

    public void addOrderToStore(Order order){
        orderService.addOrderToStore(order);
    }

    public void executeOrder(Order order){
        orderService.executeOrder(order);
    }


    public List<Order> getListOfOrders() {
        List<Order> orders = orderService.getListOfOrders();
        return orders;
    }

    public void setListOfOrders(List<Order> orders){
        orderService.setListOfOrders(orders);
    }

    public Order createOrder(List<Book> books, Customer customer, LocalDate dateOfDoneOrder){
      Order order =  orderService.createOrder(books, customer, dateOfDoneOrder);
      return order;
    }

    public Order getOrderById(int id){
        Order order = orderService.getOrderById(id);
        return order;
    }

    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    public void addOrderToListOfOrders(Order order){
        orderService.addOrderToListOfOrders(order);
    }

    public void showListOfOrders(){
        orderService.showListOfOrders();
    }

    public void sortOrdersByDateOfDone(){
        orderService.sortOrdersByDateOfDone();
    }

    public void sortOrdersByPrice(){
        orderService.sortOrdersByPrice();
    }

    public void sortOrdersByStatus(){
        orderService.sortOrdersByStatus();
    }

    public void showDetailsOfOrder(Order order){
        orderService.showDetailsOfOrder(order);
    }

    public void deleteOrder(Order order){
        orderService.deleteOrder(order);
    }

    public void changeOrderStatusToCancelled(Order order){
        orderService.changeOrderStatusToCancelled(order);
    }

    public  void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2){
        orderService.countOfDoneOrdersByPeriodOfTime(orders, date1, date2);
    }


}
