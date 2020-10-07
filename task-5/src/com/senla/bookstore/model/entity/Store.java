package com.senla.bookstore.model.entity;

import com.senla.bookstore.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Customer> listOfCustomers;
    private List<Order> listOfOrders = new ArrayList<Order>();
    private List<Book> listOfBooksInStorehouse;
    private List<RequestForBook> listOfRequestBooks = new ArrayList<RequestForBook>();
    private Stock stock;

    public Store(Stock stock, List<Book> listOfBooksInStorehouse, List<Customer> listOfCustomers) {
        this.stock = stock;
        this.listOfBooksInStorehouse = listOfBooksInStorehouse;
        this.listOfCustomers = listOfCustomers;
    }

    public Store(){

    }


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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
