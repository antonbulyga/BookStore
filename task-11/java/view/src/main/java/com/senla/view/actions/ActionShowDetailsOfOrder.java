package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;
import java.util.List;

public class ActionShowDetailsOfOrder implements IAction {

    @Override
    public void execute() throws IOException {
        int number = 0;
        OrderController.getInstance().showListOfOrders();
        List<Order> listOfOrdersInStore = OrderController.getInstance().getListOfOrders();
        while (number == 0){
            System.out.println("Fill in the number of order");
            number = IntegerInput.getInputInteger();
            if(number < 0){
                number = 0;
            }
        }
        OrderController.getInstance().showDetailsOfOrder(listOfOrdersInStore.get(number));
    }
}
