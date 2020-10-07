package com.senla.model.utils;

import com.senla.model.entity.Customer;

public class CustomerStringExportBuilder {
    public static String customerStringBuilder(Customer customer){
        StringBuilder stringOfCustomer = new StringBuilder();
        stringOfCustomer.append(customer.getId() + "," + customer.getAge()+ "," + customer.getName());
        String s = stringOfCustomer.toString();
        return s;
    }
}
