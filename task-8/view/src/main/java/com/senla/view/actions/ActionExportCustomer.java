package main.java.com.senla.view.actions;

import annotation.MyInject;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportCustomer implements IAction {
    @MyInject(key = "customerFile")
    private String path = null;

    @Override
    public void execute(){
        List<Customer> customerList = CustomerController.getInstance().getListOfCustomers();
        ExportHelper.write(null, null, customerList, null, path);

    }
}
