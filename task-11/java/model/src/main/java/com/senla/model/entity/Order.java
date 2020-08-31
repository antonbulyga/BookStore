package com.senla.model.entity;

import com.senla.model.enumeration.OrderStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Order implements Serializable {
    private static final long serialVersionUID = -8721753150087324417L;
    private int id;
    private LocalDate dateOfOrder;
    private LocalDate dateOfDoneOrder;
    private List<Book> books = new ArrayList<>();
    private double priceOfOrder;
    private OrderStatus orderStatus;
    private Customer customer;
    private List<RequestForBook> listOfRequestForBooks = new ArrayList<>();

    public Order(int id, LocalDate dateOfOrder, LocalDate dateOfDoneOrder, List<Book> books, OrderStatus orderStatus, Customer customer, int priceOfOrder) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDoneOrder = dateOfDoneOrder;
        this.books = books;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.priceOfOrder = priceOfOrder;
    }

    public Order(){

    }

    public List<RequestForBook> getListOfRequestForBooks() {
        return listOfRequestForBooks;
    }

    public void setListOfRequestForBooks(List<RequestForBook> listOfRequestForBooks) {
        this.listOfRequestForBooks = listOfRequestForBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public double getPriceOfOrder() {
        return priceOfOrder;
    }

    public void setPriceOfOrder(double priceOfOrder) {
        this.priceOfOrder = priceOfOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



}
