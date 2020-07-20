package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.view.api.IAction;

import java.io.*;
import java.util.List;
@Component
public class ActionImportCustomer implements IAction {
    @MyInject(key = "customerFile")
    private String path;

    @Override
    public void execute(){
        List<Customer> customerList = CustomerController.getInstance().getListOfCustomers();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
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
