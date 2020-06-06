package com.senla.bookstore.model;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private BookStatus bookStatus;
    private StockLevel stockLevel;
    private RequestForBook requestForBook;
    private LocalDate arriveDate;
    private LocalDate publicationDate;



    public Book(int id, String title, String author, double price, BookStatus bookStatus, StockLevel stockLevel, RequestForBook requestForBook, LocalDate arriveDate, LocalDate publicationDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.bookStatus = bookStatus;
        this.stockLevel = stockLevel;
        this.requestForBook = requestForBook;
        this.arriveDate = arriveDate;
        this.publicationDate = publicationDate;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StockLevel getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(StockLevel stockLevel) {
        this.stockLevel = stockLevel;
    }
    public RequestForBook getRequestForBook() {
        return requestForBook;
    }

    public void setRequestForBook(RequestForBook requestForBook) {
        this.requestForBook = requestForBook;
    }
    public String getAuthor() {
        return author;
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
