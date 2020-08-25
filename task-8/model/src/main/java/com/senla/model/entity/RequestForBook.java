package main.java.com.senla.model.entity;

import main.java.com.senla.model.enumeration.RequestForBookStatus;

import java.io.Serializable;

public class RequestForBook implements Serializable {
    private static final long serialVersionUID = -4009735170071392702L;
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
