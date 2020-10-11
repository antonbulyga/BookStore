package com.senla.model.service;

import com.senla.model.entity.Customer;
import com.senla.model.repository.api.CustomerRepository;
import com.senla.model.service.api.CustomerService;
import com.senla.model.utils.ExportHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    @Value("${customerFile}")
    private String path;
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void importCustomer(){
        List<Customer> customerList = getListOfCustomers();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                int age = Integer.parseInt(strings[1]);
                String name = strings[2];
                Customer customer = new Customer(id, age, name);
                for (int i = 0; i < customerList.size(); i++) {
                    if(customer.getId() == customerList.get(i).getId()){
                        customerRepository.update(customer);
                        count++;
                    }
                }
                if (count == 0) {
                    customerRepository.create(customer);
                }
                count = 0;
            }

        } catch (IOException | SQLException e) {
            logger.error("We have no file");
        }
    }

    public void exportCustomer(){
        List<Customer> customerList = getListOfCustomers();
        ExportHelper.write(null, null, customerList, null, path);
    }

    public List<Customer> getListOfCustomers() {
        List<Customer> customers = customerRepository.getAll();
        if (customers.isEmpty()) {
            throw new NoResultException("No customers in the database");
        }
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

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

    public Customer getCustomerById(int id) {
        Customer customer =  customerRepository.read(id);
        if(customer == null) {
            throw new NoResultException("No book with this ID");
        }
        return customer;
    }

}
