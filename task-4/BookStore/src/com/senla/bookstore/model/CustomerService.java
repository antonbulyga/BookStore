package com.senla.bookstore.model;

import com.senla.bookstore.service.Store;

public class CustomerService {
    public Customer[] addCustomer(Store store, Customer customer){
        Customer[] arrayOfCustomer = store.getArrayOfCustomers();
        Customer[] copyOfArray = new Customer[arrayOfCustomer.length + 1];
        System.arraycopy(arrayOfCustomer, 0, copyOfArray, 0, arrayOfCustomer.length);
        copyOfArray[copyOfArray.length - 1] = customer;
        store.setArrayOfCustomers(copyOfArray);
        return copyOfArray;
    }

}
