package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private static CustomerService instance;

    private CustomerService() {

    }

    public static CustomerService getInstance(){
        if(instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

    public List<Customer> getListOfCustomers(){
        List<Customer> customers = CustomerRepository.getInstance().getListOfCustomers();
        return customers;
    }

    public void setListOfCustomers(List<Customer> customers){
        CustomerRepository.getInstance().setListOfCustomers(customers);
    }

    public void addCustomerToListOfCustomers(Customer customer){
        CustomerRepository.getInstance().addCustomerToListOfCustomers(customer);
    }


    public Customer createCustomer(int id, int age, String name){
     Customer customer = CustomerRepository.getInstance().createCustomer(id, age, name);
      return customer;
    }

    public void updateCustomer(Customer customer){
      CustomerRepository.getInstance().updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer){
        CustomerRepository.getInstance().deleteCustomer(customer);
    }

    public Customer getCustomerById(int id){
       Customer customer = CustomerRepository.getInstance().getCustomerById(id);
       return customer;
    }


}
