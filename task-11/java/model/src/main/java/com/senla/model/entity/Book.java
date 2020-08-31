package com.senla.model.entity;

import java.io.Serializable;
import java.time.LocalDate;


public class Book implements Serializable {
    private static final long serialVersionUID = -374865195154915227L;
    private int id;
    private String title;
    private String author;
    private double price;
    private LocalDate arriveDate;
    private LocalDate publicationDate;

    public Book(int id, String title, String author, double price, LocalDate arriveDate, LocalDate publicationDate) {
        this.id = id;
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
}
