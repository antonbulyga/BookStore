package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.Store;
import com.senla.bookstore.service.OrderService;
import com.senla.bookstore.view.IAction;

import java.util.List;

public class ActionSortOrdersByPrice implements IAction {
    OrderService orderService = new OrderService();
    Store store = new Store();

    @Override
    public void execute() {
        List<Order> listOfOrdersInStore = store.getListOfOrders();
        orderService.sortOrdersByPrice(listOfOrdersInStore);
    }
}
