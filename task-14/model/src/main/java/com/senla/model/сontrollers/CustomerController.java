package com.senla.model.сontrollers;

import com.senla.model.entity.Customer;
import com.senla.model.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private CustomerController(){

    }

    public static CustomerController getCustomerControllerBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerController customerController = context.getBean(CustomerController.class);
        return customerController;
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
