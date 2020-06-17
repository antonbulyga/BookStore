package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.service.StoreService;
import com.senla.bookstore.view.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class ActionCountOfDoneOrdersByPeriodOfTime implements IAction {
    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the first date (YYYY,MM,DD): ");
        String date1 = reader.readLine();
        System.out.println("Please enter the second date (YYYY,MM,DD): ");
        String date2 = reader.readLine();
        int year1 = Integer.parseInt(date1.substring(0,4));
        int month1 = Integer.parseInt(date1.substring(5,7));
        int day1 = Integer.parseInt(date1.substring(8,10));
        LocalDate firstDate = LocalDate.of(year1, month1, day1);

        int year2 = Integer.parseInt(date2.substring(0,4));
        int month2 = Integer.parseInt(date2.substring(5,7));
        int day2 = Integer.parseInt(date2.substring(8,10));
        LocalDate secondDate = LocalDate.of(year2, month2, day2);


        List<Order> listOfOrdersInStore = StoreService.getInstance().getStore().getListOfOrders();
        OrderController.getInstance().countOfDoneOrdersByPeriodOfTime(listOfOrdersInStore, firstDate, secondDate);

    }
}
