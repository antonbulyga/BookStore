package com.senla.model.сontrollers;

import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    public void importOrder(){
        orderService.importOrder();
    }

    public void exportOrder(){
        orderService.exportOrder();
    }

    public void sumOfMoneyPerPeriodOfTime(LocalDate date1, LocalDate date2) {
        orderService.sumOfMoneyPerPeriodOfTime(date1, date2);
    }

    public void executeOrder(Order order){
        orderService.executeOrder(order);
    }


    public List<Order> getListOfOrders() {
        List<Order> orders = orderService.getListOfOrders();
        return orders;
    }

    public Order createOrder(List<Book> books, List<RequestForBook> requestForBooks, Customer customer, LocalDate dateOfDoneOrder){
      Order order =  orderService.createOrder(books, requestForBooks, customer, dateOfDoneOrder);
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
