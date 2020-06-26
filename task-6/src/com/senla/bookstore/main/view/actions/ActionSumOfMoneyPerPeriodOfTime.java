package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.service.StoreService;
import com.senla.bookstore.main.model.utils.input.IntegerInput;
import com.senla.bookstore.main.model.utils.input.StringInput;
import com.senla.bookstore.main.model.сontrollers.StoreController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ActionSumOfMoneyPerPeriodOfTime implements IAction {
    @Override
    public void execute() throws IOException {
        String firstDateString = null;
        String secondDateString = null;
        LocalDate firstDate = null;
        LocalDate secondDate = null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");

        System.out.println("Please enter the first date (dd,MM,yyyy): ");
        while (firstDateString == null){
            System.out.println("Fill in the publication date");
            firstDateString = StringInput.getStringInput();
            if(firstDateString.equals(" ")){
                firstDateString = null;
            }
        }
        try {
            firstDate = LocalDate.parse(firstDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            System.out.println("Incorrect input date");
        }

        System.out.println("Please enter the second date (dd,MM,yyyy): ");
        while (secondDateString == null){
            System.out.println("Fill in the publication date");
            secondDateString = StringInput.getStringInput();
            if(secondDateString.equals(" ")){
                secondDateString = null;
            }
        }
        try {
            secondDate = LocalDate.parse(secondDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            System.out.println("Incorrect input date");
        }

        List<Order> listOfOrdersInStore = StoreService.getInstance().getStore().getListOfOrders();
        StoreController.getInstance().sumOfMoneyPerPeriodOfTime(listOfOrdersInStore, firstDate, secondDate);

    }
}
