package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportCustomer implements IAction {

    @Override
    public void execute(){
        CustomerController.getInstance().exportCustomer();
    }
}
