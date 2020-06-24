package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.service.StoreService;
import com.senla.bookstore.main.model.utils.IntegerInput;
import com.senla.bookstore.main.view.api.IAction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ActionSumOfMoneyPerPeriodOfTime implements IAction {
    @Override
    public void execute() throws IOException {
        String date1 = null;
        String date2 = null;
        int year1 = 0;
        int month1 = 0;
        int day1 = 0;
        int year2 = 0;
        int month2 = 0;
        int day2 = 0;

        System.out.println("Please enter the first date (YYYY,MM,DD): ");
        while (year1 == 0){
            System.out.println("Enter year");
            year1 = IntegerInput.getInputInteger();
            if(year1 < 0 || year1 > 2020){
                year1 = 0;
            }
        }
        while (month1 == 0){
            System.out.println("Enter month");
            month1 = IntegerInput.getInputInteger();
            if(month1 < 0 || month1 > 12){
                month1 = 0;
            }
        }

        while (day1 == 0){
            System.out.println("Enter day");
            day1 = IntegerInput.getInputInteger();
            if(day1 < 0 || day1 > 31){
                day1 = 0;
            }
        }
        LocalDate firstDate = LocalDate.of(year1, month1, day1);

        System.out.println("Please enter the second date (YYYY,MM,DD): ");
        while (year2 == 0){
            System.out.println("Enter year");
            year2 = IntegerInput.getInputInteger();
            if(year2 < 0 || year2 > 2020){
                year2 = 0;
            }
        }
        while (month2 == 0){
            System.out.println("Enter month");
            month2 = IntegerInput.getInputInteger();
            if(month2 < 0 || month2 > 12){
                month2 = 0;
            }
        }

        while (day2 == 0){
            System.out.println("Enter day");
            day2 = IntegerInput.getInputInteger();
            if(day2 < 0 || day2 > 31){
                day2 = 0;
            }
        }
        LocalDate secondDate = LocalDate.of(year2, month2, day2);

        List<Order> listOfOrdersInStore = StoreService.getInstance().getStore().getListOfOrders();
        StoreService.getInstance().sumOfMoneyPerPeriodOfTime(listOfOrdersInStore, firstDate, secondDate);

    }
}
