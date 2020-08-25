package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.DAO.Dao;
import main.java.com.senla.model.entity.Customer;

import java.util.List;

public interface CustomerRepository extends Dao<Customer, Integer> {
    boolean create (Customer customer);
    boolean update (Customer customer);
    boolean delete (Customer customer);
    Customer read(Integer id);
    List<Customer> getAll();







   /* void addCustomerToListOfCustomers(Customer customer);

    Customer createCustomer(int id, int age, String name);

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Customer getCustomerById(int id);

    List<Customer> getListOfCustomers();

    void setListOfCustomers(List<Customer> listOfCustomers);*/

}
