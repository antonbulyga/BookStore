package com.senla.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "arrive_date", nullable = false)
    private LocalDate arriveDate;
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;
    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Book(String title, String author, double price, LocalDate arriveDate, LocalDate publicationDate) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.arriveDate = arriveDate;
        this.publicationDate = publicationDate;
    }

    public Book(int id, String title, String author, double price, LocalDate arriveDate, LocalDate publicationDate) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.arriveDate = arriveDate;
        this.publicationDate = publicationDate;
    }

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }
    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
