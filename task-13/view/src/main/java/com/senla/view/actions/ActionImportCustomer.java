package com.senla.view.actions;

import com.senla.model.сontrollers.CustomerController;
import com.senla.view.api.IAction;

public class ActionImportCustomer implements IAction {

    @Override
    public void execute(){
        CustomerController.getInstance().importCustomer();
    }
}
