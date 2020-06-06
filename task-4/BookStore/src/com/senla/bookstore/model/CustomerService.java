package com.senla.bookstore.model;

public class CustomerService {
    public Customer[] addCustomer(Customer[] arrayOfCustomer, Customer customer){
        Customer[] copyOfArray = new Customer[arrayOfCustomer.length + 1];
        System.arraycopy(arrayOfCustomer, 0, copyOfArray, 0, arrayOfCustomer.length);
        copyOfArray[copyOfArray.length - 1] = customer;
        return copyOfArray;
    }
}
