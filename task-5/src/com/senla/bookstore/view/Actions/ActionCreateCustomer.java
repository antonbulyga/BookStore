package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Customer;
import com.senla.bookstore.model.сontrollers.CustomerController;
import com.senla.bookstore.view.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActionCreateCustomer implements IAction {

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Fill in the id of the customer");
        int customerId = Integer.parseInt(reader.readLine());
        System.out.println("Fill in the age of the customer");
        int customerAge = Integer.parseInt(reader.readLine());
        System.out.println("Fill in the name of the customer");
        String customerName = reader.readLine();
        Customer customer = new Customer(customerId, customerAge, customerName);

        CustomerController.getInstance().addCustomerToListOfCustomers(customer);
    }
}

