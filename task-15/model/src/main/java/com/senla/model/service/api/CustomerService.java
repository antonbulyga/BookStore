package com.senla.model.service.api;

import com.senla.model.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    void importCustomer();

    void exportCustomer();

    List<Customer> getListOfCustomers();

    void addCustomerToListOfCustomers(Customer customer) throws SQLException;

    Customer createCustomer(int id, int age, String name) throws SQLException;

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Customer getCustomerById(int id);

}
