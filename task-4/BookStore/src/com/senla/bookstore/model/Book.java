package com.senla.bookstore.model;

import java.time.LocalDate;


public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private BookStatus bookStatus;
    private RequestForBook[] requestForBooks;
    private LocalDate arriveDate;
    private LocalDate publicationDate;

    public Book(int id, String title, String author, double price, BookStatus bookStatus, RequestForBook[] requestForBooks, LocalDate arriveDate, LocalDate publicationDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.bookStatus = bookStatus;
        this.requestForBooks = requestForBooks;
        this.arriveDate = arriveDate;
        this.publicationDate = publicationDate;
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public RequestForBook[] getRequestForBooks() {
        return requestForBooks;
    }

    public void setRequestForBooks(RequestForBook[] requestForBooks) {
        this.requestForBooks = requestForBooks;
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
