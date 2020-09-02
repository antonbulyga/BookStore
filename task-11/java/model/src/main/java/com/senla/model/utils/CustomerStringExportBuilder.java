package com.senla.model.utils;

import com.senla.model.entity.Customer;

public class CustomerStringExportBuilder {
    public static String customerStringBuilder(Customer customer){
        String string = null;
        StringBuilder stringOfCustomer = new StringBuilder(string);
        stringOfCustomer.append(customer.getId() + " " + customer.getName() + " " + customer.getAge());
        return string;
    }
}
