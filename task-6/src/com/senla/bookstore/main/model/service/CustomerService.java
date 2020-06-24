package com.senla.bookstore.main.model.service;

import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.entity.Store;

import java.util.List;

public class CustomerService {
    private static CustomerService instance;
    private Store store;

    private CustomerService() {
        store = StoreService.getInstance().getStore();
    }

    public static CustomerService getInstance(){
        if(instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

    public List<Customer> getListOfCustomers(){
        List<Customer> customers = store.getListOfCustomers();
        return customers;
    }

    public void addCustomerToListOfCustomers(Customer customer){
        List<Customer> customers = store.getListOfCustomers();
        customers.add(customer);
        store.setListOfCustomers(customers);
    }

    public Customer createCustomer(int id, int age, String name){
        Customer customer = new Customer(id, age, name);
        return customer;
    }


}
