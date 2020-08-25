package main.java.com.senla.model.entity;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 7400651916257654955L;
    private int id;
    private int age;
    private String name;

    public Person (int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
    public Person(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
