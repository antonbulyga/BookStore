package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static OrderRepository instance;
    private List<Order> listOfOrders = new ArrayList<>();

    private OrderRepository(){

    }

    public static OrderRepository getInstance(){
        if(instance == null){
            instance = new OrderRepository();
        }
        return instance;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }
}
