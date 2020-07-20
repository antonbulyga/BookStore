package main.java.com.senla.model.сontrollers;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.service.api.CustomerService;

import java.util.List;

public class CustomerController {
    private static CustomerController instance;
    @MyAutoWired
    private CustomerService customerService;

    private CustomerController(){

    }

    public static CustomerController getInstance(){
        if(instance == null){
            instance = new CustomerController();
        }
        return instance;
    }


    public List<Customer> getListOfCustomers(){
       List<Customer> customers = customerService.getListOfCustomers();
        return customers;
    }

    public void setListOfCustomers(List<Customer> customers){
        customerService.setListOfCustomers(customers);
    }

    public void addCustomerToListOfCustomers(Customer customer){
        customerService.addCustomerToListOfCustomers(customer);
    }

    public Customer createCustomer(int id, int age, String name){
       Customer customer = customerService.createCustomer(id, age, name);
        return customer;
    }

    public Customer getCustomerById(int id){
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    public void updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
    }
}
