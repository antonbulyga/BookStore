package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.service.CustomerService;

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

    public Customer createCustomer(int id, int age, String name){
       Customer customer = CustomerService.getInstance().createCustomer(id, age, name);
        return customer;
    }

    public Customer getCustomerById(int id){
        Customer customer = CustomerService.getInstance().getCustomerById(id);
        return customer;
    }

    public void updateCustomer(Customer customer){
        CustomerService.getInstance().updateCustomer(customer);
    }
}
