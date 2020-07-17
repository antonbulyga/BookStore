package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.service.CustomerServiceImpl;

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
       List<Customer> customers = CustomerServiceImpl.getInstance().getListOfCustomers();
        return customers;
    }

    public void setListOfCustomers(List<Customer> customers){
        CustomerServiceImpl.getInstance().setListOfCustomers(customers);
    }

    public void addCustomerToListOfCustomers(Customer customer){
        CustomerServiceImpl.getInstance().addCustomerToListOfCustomers(customer);
    }

    public Customer createCustomer(int id, int age, String name){
       Customer customer = CustomerServiceImpl.getInstance().createCustomer(id, age, name);
        return customer;
    }

    public Customer getCustomerById(int id){
        Customer customer = CustomerServiceImpl.getInstance().getCustomerById(id);
        return customer;
    }

    public void updateCustomer(Customer customer){
        CustomerServiceImpl.getInstance().updateCustomer(customer);
    }
}
