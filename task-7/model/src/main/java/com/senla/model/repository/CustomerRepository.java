package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static CustomerRepository instance;
    private List<Customer> listOfCustomers = new ArrayList<>();

    private CustomerRepository(){

    }

    public static CustomerRepository getInstance(){
        if(instance == null){
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void addCustomerToListOfCustomers(Customer customer){
        List<Customer> customers = CustomerRepository.getInstance().getListOfCustomers();
        customers.add(customer);
        CustomerRepository.getInstance().setListOfCustomers(customers);
    }

    public Customer createCustomer(int id, int age, String name){
        Customer customer = new Customer(id, age, name);
        return customer;
    }

    public void updateCustomer(Customer customer){
        List<Customer> customerList = CustomerRepository.getInstance().getListOfCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if(customer.getId() == customerList.get(i).getId()){
                deleteCustomer(customerList.get(i));
                customerList.set(i, customer);
                setListOfCustomers(customerList);
            }
        }
    }

    public void deleteCustomer(Customer customer){
        List<Customer> customerList = CustomerRepository.getInstance().getListOfCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getId() == customer.getId()){
                customerList.remove(customerList.get(i));
            }
            CustomerRepository.getInstance().setListOfCustomers(customerList);
        }
    }

    public Customer getCustomerById(int id){
        List<Customer> customers = CustomerRepository.getInstance().getListOfCustomers();
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId() == id){
                customer = customers.get(i);
            }
        }
        return customer;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }
}
