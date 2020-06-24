package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ActionImportCustomer implements IAction {

    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
             try (BufferedReader reader = new BufferedReader(new FileReader(property.getProperty("customerFile")))) {
               String line;
                while ((line = reader.readLine()) != null) {
                  while (true) {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    String name = strings[1];
                    int age = Integer.parseInt(strings[2]);
                    Customer customer = CustomerController.getInstance().createCustomer(id, age, name);
                    CustomerController.getInstance().addCustomerToListOfCustomers(customer);
                    break;
                  }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }
}
