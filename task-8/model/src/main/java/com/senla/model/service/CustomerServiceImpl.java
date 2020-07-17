package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.repository.CustomerRepositoryImpl;
import main.java.com.senla.model.service.api.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static CustomerServiceImpl instance;

    private CustomerServiceImpl() {

    }

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    public List<Customer> getListOfCustomers() {
        List<Customer> customers = CustomerRepositoryImpl.getInstance().getListOfCustomers();
        return customers;
    }

    public void setListOfCustomers(List<Customer> customers) {
        CustomerRepositoryImpl.getInstance().setListOfCustomers(customers);
    }

    public void addCustomerToListOfCustomers(Customer customer) {
        CustomerRepositoryImpl.getInstance().addCustomerToListOfCustomers(customer);
    }


    public Customer createCustomer(int id, int age, String name) {
        Customer customer = CustomerRepositoryImpl.getInstance().createCustomer(id, age, name);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        CustomerRepositoryImpl.getInstance().updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        CustomerRepositoryImpl.getInstance().deleteCustomer(customer);
    }

    public Customer getCustomerById(int id) {
        Customer customer = CustomerRepositoryImpl.getInstance().getCustomerById(id);
        return customer;
    }


}
