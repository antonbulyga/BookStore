package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.Store;
import com.senla.bookstore.service.StoreService;
import com.senla.bookstore.view.IAction;

import java.time.LocalDate;
import java.util.List;

public class ActionCountOfDoneOrdersByPeriodOfTime implements IAction {
    Store store = new Store();
    StoreService storeService = new StoreService();
    LocalDate date1 = LocalDate.of(2020, 06, 01);
    LocalDate date2 = LocalDate.of(2020, 06, 07);

    @Override
    public void execute() {
        List<Order> listOfOrdersInStore = store.getListOfOrders();
        storeService.countOfDoneOrdersByPeriodOfTime(listOfOrdersInStore, date1, date2);

    }
}
