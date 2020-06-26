package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.utils.ExportHelper;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.view.api.IAction;

import java.util.List;

public class ActionExportCustomer implements IAction {
    @Override
    public void execute(){
        List<Customer> customerList = CustomerController.getInstance().getListOfCustomers();
        ExportHelper.write(null, null, customerList, null, "customerFile");

    }
}
