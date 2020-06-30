package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

public class ActionShowListOfOrders implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().showListOfOrders();
    }
}
