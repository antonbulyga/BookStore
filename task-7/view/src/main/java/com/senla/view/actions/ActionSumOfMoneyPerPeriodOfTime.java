package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.repository.OrderRepositoryImpl;
import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

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

        List<Order> listOfOrdersInStore = OrderRepositoryImpl.getInstance().getListOfOrders();
        OrderController.getInstance().sumOfMoneyPerPeriodOfTime(listOfOrdersInStore, firstDate, secondDate);

    }
}
