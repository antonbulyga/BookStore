package main.java.com.senla.model.service;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.repository.api.CustomerRepository;
import main.java.com.senla.model.service.api.CustomerService;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.CustomerController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                Customer customer = customerRepository.createCustomer(id, age, name);
                for (int i = 0; i < customerList.size(); i++) {
                    if(customer.getId() == customerList.get(i).getId()){
                        updateCustomer(customer);
                    }
                    else {
                        customerRepository.addCustomerToListOfCustomers(customer);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }

    public void exportCustomer(){
        List<Customer> customerList = getListOfCustomers();
        ExportHelper.write(null, null, customerList, null, path);
    }

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
