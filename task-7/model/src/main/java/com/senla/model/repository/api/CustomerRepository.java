package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    void addCustomerToListOfCustomers(Customer customer);

    Customer createCustomer(int id, int age, String name);

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Customer getCustomerById(int id);

    List<Customer> getListOfCustomers();

    void setListOfCustomers(List<Customer> listOfCustomers);

}
