package com.senla.model.сontrollers;

import com.senla.config.annotations.MyAutoWired;
import com.senla.model.entity.Customer;
import com.senla.model.service.api.CustomerService;

import java.sql.SQLException;
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

    public void importCustomer(){
        customerService.importCustomer();
    }

    public void exportCustomer(){
        customerService.exportCustomer();
    }

    public List<Customer> getListOfCustomers(){
       List<Customer> customers = customerService.getListOfCustomers();
        return customers;
    }

    public void addCustomerToListOfCustomers(Customer customer) throws SQLException {
        customerService.addCustomerToListOfCustomers(customer);
    }

    public Customer createCustomer(int id, int age, String name) throws SQLException {
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
