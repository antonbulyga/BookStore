package com.senla.bookstore.main.model.entity;

import com.senla.bookstore.main.model.сomparators.RequestForBookStatus;

public class RequestForBook {
    private int id;
    private Book book;
    private RequestForBookStatus requestStatus;
    private Order order;

    public RequestForBook(int id, Book book, RequestForBookStatus requestStatus, Order order) {
        this.id = id;
        this.book = book;
        this.requestStatus = requestStatus;
        this.order = order;
    }

    public RequestForBook(){

    }

    @Override
    public String toString() {
        return  this.id
                + "," + this.book.getId()
                + "," + this.requestStatus
                + "," + this.order.getId();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public RequestForBookStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestForBookStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
