package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.utils.PropertyPath;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class ActionImportCustomer implements IAction {

    @Override
    public void execute(){
        List<Customer> customerList = CustomerController.getInstance().getListOfCustomers();
        try {
             BufferedReader reader = new BufferedReader(new FileReader(PropertyPath.choose("customerFile")));
               String line;
                while ((line = reader.readLine()) != null) {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    String name = strings[1];
                    int age = Integer.parseInt(strings[2]);
                    Customer customer = CustomerController.getInstance().createCustomer(id, age, name);
                    for (int i = 0; i < customerList.size(); i++) {
                        if(customer.getId() == customerList.get(i).getId()){
                            CustomerController.getInstance().updateCustomer(customer);
                        }
                        else {
                            CustomerController.getInstance().addCustomerToListOfCustomers(customer);
                        }
                    }
                  }

        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }
}
