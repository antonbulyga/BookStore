package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Store;

import java.util.List;

public class CustomerService {
    private static CustomerService instance;
    private Store store;

    private CustomerService() {
        store = StoreService.getInstance().getStore();
    }

    public static CustomerService getInstance(){
        if(instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

    public List<Customer> getListOfCustomers(){
        List<Customer> customers = store.getListOfCustomers();
        return customers;
    }

    public void addCustomerToListOfCustomers(Customer customer){
        List<Customer> customers = store.getListOfCustomers();
        customers.add(customer);
        store.setListOfCustomers(customers);
    }

    public Customer createCustomer(int id, int age, String name){
        Customer customer = new Customer(id, age, name);
        return customer;
    }

    public void updateCustomer(Customer customer){
        List<Customer> customerList = store.getListOfCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if(customer.getId() == customerList.get(i).getId()){
               deleteCustomer(customerList.get(i));
               customerList.add(customer);
            }
        }

    }

    public void deleteCustomer(Customer customer){
        List<Customer> customerList = store.getListOfCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getId() == customer.getId()){
                customerList.remove(customerList.get(i));
            }
            store.setListOfCustomers(customerList);
        }

    }

    public Customer getCustomerById(int id){
        List<Customer> customers = store.getListOfCustomers();
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId() == id){
                customer = customers.get(i);
            }
        }
        return customer;
    }


}
