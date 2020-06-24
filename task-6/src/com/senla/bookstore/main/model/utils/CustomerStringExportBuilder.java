package com.senla.bookstore.main.model.utils;

import com.senla.bookstore.main.model.entity.Customer;

public class CustomerStringExportBuilder {
    public static String customerStringBuilder(Customer customer){
        String string = null;
        StringBuilder stringOfCustomer = new StringBuilder(string);
        stringOfCustomer.append(customer.getId() + " " + customer.getName() + " " + customer.getAge());
        return string;
    }
}
