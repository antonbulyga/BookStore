package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;

public class ActionImportOrder implements IAction {

    @Override
    public void execute() throws IOException {
        OrderController.getInstance().importOrder();
    }
}
