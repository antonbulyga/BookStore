package com.senla.bookstore.model.сontrollers;

import com.senla.bookstore.model.entity.Customer;
import com.senla.bookstore.model.service.CustomerService;

import java.util.List;

public class CustomerController {
    private static CustomerController instance;

    private CustomerController(){

    }

    public static CustomerController getInstance(){
        if(instance == null){
            instance = new CustomerController();
        }
        return instance;
    }

    public List<Customer> getListOfCustomers(){
       List<Customer> customers = CustomerService.getInstance().getListOfCustomers();
        return customers;
    }

    public void addCustomerToListOfCustomers(Customer customer){
        CustomerService.getInstance().addCustomerToListOfCustomers(customer);
    }
}
