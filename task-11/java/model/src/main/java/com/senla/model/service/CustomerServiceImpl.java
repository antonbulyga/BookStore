package main.java.com.senla.model.service;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.DAO.Dao;
import main.java.com.senla.model.repository.api.CustomerRepository;
import main.java.com.senla.model.service.api.CustomerService;
import main.java.com.senla.model.utils.ExportHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@Component
public class CustomerServiceImpl implements CustomerService {
    @MyAutoWired
    private CustomerRepository customerRepository;
    @MyInject(key = "customerFile")
    private String path;

    public void importCustomer(){
        List<Customer> customerList = getListOfCustomers();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String name = strings[1];
                int age = Integer.parseInt(strings[2]);
                Customer customer = new Customer(id, age, name);
                customerRepository.create(customer);
                for (int i = 0; i < customerList.size(); i++) {
                    if(customer.getId() == customerList.get(i).getId()){
                        customerRepository.update(customer);
                    }
                    else {
                        customerRepository.create(customer);
                    }
                }
            }

        } catch (IOException | SQLException e) {
            System.err.println("We have no file");
        }
    }

    public void exportCustomer(){
        List<Customer> customerList = getListOfCustomers();
        ExportHelper.write(null, null, customerList, null, path);
    }

    public List<Customer> getListOfCustomers() {
        List<Customer> customers = customerRepository.getAll();
        return customers;
    }


    public void addCustomerToListOfCustomers(Customer customer) throws SQLException {
        customerRepository.create(customer);
    }


    public Customer createCustomer(int id, int age, String name) throws SQLException {
        Customer customer = new Customer(id, age, name);
        customerRepository.create(customer);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer getCustomerById(int id) {
        Customer customer =  customerRepository.read(id);
        return customer;
    }


}
