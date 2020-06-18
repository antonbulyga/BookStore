package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.entity.Order;
import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.model.service.StoreService;
import com.senla.bookstore.view.api.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ActionShowDetailsOfOrder implements IAction {

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OrderController.getInstance().showListOfOrders();
        List<Order> listOfOrdersInStore = StoreService.getInstance().getStore().getListOfOrders();
        System.out.println("Fill in the number of order");
        int number = Integer.parseInt(reader.readLine());
        OrderController.getInstance().showDetailsOfOrder(listOfOrdersInStore.get(number));
    }
}
