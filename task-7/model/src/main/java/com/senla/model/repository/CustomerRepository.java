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

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }
}
