package main.java.com.senla.model.entity;

import java.io.Serializable;

public class Customer extends Person implements Serializable {
    private static final long serialVersionUID = -7911379113437851982L;

    public Customer(int id, int age, String name) {
        super(id, age, name);
    }
    public Customer(){

    }

}
