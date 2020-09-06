package com.senla.model.utils.generators;

public class CustomerIdGenerator {
    private static int customerId = 0;

    public static int getCustomerId(){
        customerId++;
        return customerId;
    }

}
