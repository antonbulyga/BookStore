package main.java.com.senla.model.entity;

import main.java.com.senla.model.сomparators.RequestForBookStatus;

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
