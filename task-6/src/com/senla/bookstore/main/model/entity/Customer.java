package com.senla.bookstore.main.model.entity;

public class Customer extends Person {
    public Customer(int id, int age, String name) {
        super(id, age, name);
    }

    @Override
    public String toString() {
        return  this.getId()
                + "," + this.getId()
                + "," + this.getName()
                + "," + this.getAge();
    }
}
