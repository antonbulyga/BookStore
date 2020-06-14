package com.senla.bookstore.model;

import com.senla.bookstore.controller.Test;
import com.senla.bookstore.model.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store {
    private List<Customer> listOfCustomers = new ArrayList<Customer>();
    private List<Order> listOfOrders = new ArrayList<Order>();
    private List<Book> listOfBooksInStorehouse = new ArrayList<Book>();
    private List<RequestForBook> listOfRequestBooks = new ArrayList<RequestForBook>();

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public List<Book> getListOfBooksInStorehouse() {
        return listOfBooksInStorehouse;
    }

    public void setListOfBooksInStorehouse(List<Book> listOfBooksInStorehouse) {
        this.listOfBooksInStorehouse = listOfBooksInStorehouse;
    }

    public List<RequestForBook> getListOfRequestBooks() {
        return listOfRequestBooks;
    }

    public void setListOfRequestBooks(List<RequestForBook> listOfRequestBooks) {
        this.listOfRequestBooks = listOfRequestBooks;
    }
}
