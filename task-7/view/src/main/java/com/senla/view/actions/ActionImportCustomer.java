package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.view.api.IAction;

public class ActionImportCustomer implements IAction {

    @Override
    public void execute(){
        CustomerController.getInstance().importCustomer();
    }
}
