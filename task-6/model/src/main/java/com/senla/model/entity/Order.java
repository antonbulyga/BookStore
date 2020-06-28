package main.java.com.senla.model.entity;

import main.java.com.senla.model.enumeration.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private int id;
    private LocalDate dateOfOrder;
    private LocalDate dateOfDoneOrder;
    private List<Book> books;
    private double priceOfOrder;
    private OrderStatus orderStatus;
    private Customer customer;
    private List<RequestForBook> listOfRequestForBooks = new ArrayList<>();

    public Order(int id, LocalDate dateOfOrder, LocalDate dateOfDoneOrder, List<Book> books, OrderStatus orderStatus, Customer customer, int priceOfOrder) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDoneOrder = dateOfDoneOrder;
        this.books = new ArrayList<>();
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.priceOfOrder = priceOfOrder;
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

    public List<RequestForBook> getArrayOfRequestForBooks() {
        return listOfRequestForBooks;
    }

    public void setArrayOfRequestForBooks(List<RequestForBook> listOfRequestForBooks) {
        this.listOfRequestForBooks = listOfRequestForBooks;
    }

}
