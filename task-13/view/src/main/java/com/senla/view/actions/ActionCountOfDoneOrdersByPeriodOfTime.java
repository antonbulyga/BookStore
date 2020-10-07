package com.senla.view.actions;

import com.senla.model.entity.Order;
import com.senla.model.utils.input.StringInput;
import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ActionCountOfDoneOrdersByPeriodOfTime implements IAction {
    static final Logger logger = Logger.getLogger(com.senla.view.actions.ActionCountOfDoneOrdersByPeriodOfTime.class);
    @Override
    public void execute(){
        String firstDateString = null;
        String secondDateString = null;
        LocalDate firstDate = null;
        LocalDate secondDate = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        logger.debug("Please enter the first date (dd,MM,yyyy): ");
        while (firstDateString == null){
            logger.debug("Fill in the publication date");
            firstDateString = StringInput.getStringInput();
            if(firstDateString.equals(" ")){
                firstDateString = null;
            }
        }

        try {
            firstDate = LocalDate.parse(firstDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            logger.error("Incorrect input date");
        }

        logger.debug("Please enter the second date (dd,MM,yyyy): ");
        while (secondDateString == null){
            logger.debug("Fill in the publication date");
            secondDateString = StringInput.getStringInput();
            if(secondDateString.equals(" ")){
                secondDateString = null;
            }
        }
        try {
            secondDate = LocalDate.parse(secondDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            logger.error("Incorrect input date");
        }


        List<Order> listOfOrdersInStore = OrderController.getInstance().getListOfOrders();
        OrderController.getInstance().countOfDoneOrdersByPeriodOfTime(listOfOrdersInStore, firstDate, secondDate);
    }
}
