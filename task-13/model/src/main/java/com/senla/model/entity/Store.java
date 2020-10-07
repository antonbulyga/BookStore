package com.senla.model.entity;

import com.senla.model.сontrollers.*;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {
    private static final long serialVersionUID = -6986537570809949800L;
    private static Store instance;
    private List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
    private List<Order> orders = OrderController.getInstance().getListOfOrders();
    private List<RequestForBook> requestForBooks = RequestForBookController.getInstance().getListOfRequestForBook();
    private List<Customer> customers = CustomerController.getInstance().getListOfCustomers();

    private Store(){

    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }


    public static void setInstance(Store instance) {
        Store.instance = instance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<RequestForBook> getRequestForBooks() {
        return requestForBooks;
    }

    public void setRequestForBooks(List<RequestForBook> requestForBooks) {
        this.requestForBooks = requestForBooks;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
