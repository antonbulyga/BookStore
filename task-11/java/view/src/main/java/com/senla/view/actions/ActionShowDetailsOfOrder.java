package com.senla.view.actions;

import com.senla.model.entity.Order;
import com.senla.model.utils.input.IntegerInput;
import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.List;

public class ActionShowDetailsOfOrder implements IAction {
    static final Logger logger = Logger.getLogger(ActionShowDetailsOfOrder.class);
    @Override
    public void execute() {
        BasicConfigurator.configure();
        int number = 0;
        OrderController.getInstance().showListOfOrders();
        List<Order> listOfOrdersInStore = OrderController.getInstance().getListOfOrders();
        while (number == 0){
            logger.debug("Fill in the number of order");
            number = IntegerInput.getInputInteger();
            if(number < 0){
                number = 0;
            }
        }
        OrderController.getInstance().showDetailsOfOrder(listOfOrdersInStore.get(number));
    }
}
