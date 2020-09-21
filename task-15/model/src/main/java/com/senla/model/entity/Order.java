package com.senla.model.entity;

import com.senla.model.enumeration.OrderStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -8721753150087324417L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_order", nullable = false)
    private LocalDate dateOfOrder;
    @Column (name = "date_of_done_order")
    private LocalDate dateOfDoneOrder;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order")
    private List<Book> books = new ArrayList<>();
    @Column (name = "price_of_order", nullable = false)
    private double priceOfOrder;
    @Enumerated(EnumType.STRING)
    @Column (name = "order_status", nullable = false)
    private OrderStatus orderStatus;
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<RequestForBook> listOfRequestForBooks = new ArrayList<>();

    public Order(int id, LocalDate dateOfOrder, LocalDate dateOfDoneOrder, List<Book> books, OrderStatus orderStatus, Customer customer, double priceOfOrder) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.dateOfDoneOrder = dateOfDoneOrder;
        this.books = books;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.priceOfOrder = priceOfOrder;
    }

    public Order(LocalDate dateOfOrder, LocalDate dateOfDoneOrder, List<Book> books, OrderStatus orderStatus, Customer customer, double priceOfOrder) {
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
