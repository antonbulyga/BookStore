package com.senla.bookstore.model;

import com.senla.bookstore.model.Comparators.RequestForBookStatus;

public class RequestForBook {
    private Book book;
    private RequestForBookStatus requestStatus;
    private Order order;

    public RequestForBook(Book book, RequestForBookStatus requestStatus, Order order) {
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

}
