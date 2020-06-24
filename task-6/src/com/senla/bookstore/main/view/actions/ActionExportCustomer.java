package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.utils.CustomerStringExportBuilder;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ActionExportCustomer implements IAction {
    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(property.getProperty("customerFile")))) {
            List<Customer> customerList = CustomerController.getInstance().getListOfCustomers();
            for (int i = 0; i < customerList.size(); i++) {
                writer.write(CustomerStringExportBuilder.customerStringBuilder(customerList.get(i)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }
}
