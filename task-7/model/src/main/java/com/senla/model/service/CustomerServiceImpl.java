package main.java.com.senla.model.service;

import annotation.Component;
import annotation.MyAutoWired;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.repository.CustomerRepositoryImpl;
import main.java.com.senla.model.repository.api.CustomerRepository;
import main.java.com.senla.model.service.api.CustomerService;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {
    @MyAutoWired
    private CustomerRepository customerRepository;

    public List<Customer> getListOfCustomers() {
        List<Customer> customers = customerRepository.getListOfCustomers();
        return customers;
    }

    public void setListOfCustomers(List<Customer> customers) {
        customerRepository.setListOfCustomers(customers);
    }

    public void addCustomerToListOfCustomers(Customer customer) {
        customerRepository.addCustomerToListOfCustomers(customer);
    }


    public Customer createCustomer(int id, int age, String name) {
        Customer customer = customerRepository.createCustomer(id, age, name);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }

    public Customer getCustomerById(int id) {
        Customer customer = customerRepository.getCustomerById(id);
        return customer;
    }


}
