package com.senla.bookstore.service;

import com.senla.bookstore.controller.Test;
import com.senla.bookstore.model.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class Store {
    private Customer[] arrayOfCustomers = new Customer[0];
    private Order[] arrayOfOrders = new Order[0];
    private Book[] arrayOfBooksInStorehouse = new Book[0];
    private RequestForBook[] arrayOfRequestBooks = new RequestForBook[0];

       public Customer[] getArrayOfCustomers() {
            return arrayOfCustomers;
        }

    public void setArrayOfCustomers(Customer[] arrayOfCustomers) {
        this.arrayOfCustomers = arrayOfCustomers;
    }

    public Order[] getArrayOfOrders() {
        return arrayOfOrders;
    }

    public void setArrayOfOrders(Order[] arrayOfOrders) {
        this.arrayOfOrders = arrayOfOrders;
    }

    public Book[] getArrayOfBooksInStorehouse() {
        return arrayOfBooksInStorehouse;
    }

    public void setArrayOfBooksInStorehouse(Book[] arrayOfBooksInStorehouse) {
        this.arrayOfBooksInStorehouse = arrayOfBooksInStorehouse;
    }

    public RequestForBook[] getArrayOfRequestBooks() {
        return arrayOfRequestBooks;
    }

    public void setArrayOfRequestBooks(RequestForBook[] arrayOfRequestBooks) {
        this.arrayOfRequestBooks = arrayOfRequestBooks;
    }
}
