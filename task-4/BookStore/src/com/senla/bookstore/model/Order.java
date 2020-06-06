package com.senla.bookstore.model;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private int id;
    private LocalDate dateOfOrder;
    private LocalDate dateOfDoneOrder;
    private Book[] books;
    private double priceOfOrder;
    private OrderStatus orderStatus;
    private Customer customer;
    private RequestForBook[] arrayOfRequestForBooks = new RequestForBook[0];

    public Order(int id, LocalDate dateOfOrder, LocalDate dateOfDoneOrder, Book[] books, OrderStatus orderStatus, Customer customer, int priceOfOrder) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDoneOrder = dateOfDoneOrder;
        this.books = books;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.priceOfOrder = priceOfOrder;

    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public LocalDate getDateOfDoneOrder() {
        return dateOfDoneOrder;
    }

    public void setDateOfDoneOrder(LocalDate dateOfDoneOrder) {
        this.dateOfDoneOrder = dateOfDoneOrder;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public RequestForBook[] getArrayOfRequestForBooks() {
        return arrayOfRequestForBooks;
    }

    public void setArrayOfRequestForBooks(RequestForBook[] arrayOfRequestForBooks) {
        this.arrayOfRequestForBooks = arrayOfRequestForBooks;
    }
    public double getPriceOfOrder() {
        return priceOfOrder;
    }

    public void setPriceOfOrder(double priceOfOrder) {
        this.priceOfOrder = priceOfOrder;
    }
}
