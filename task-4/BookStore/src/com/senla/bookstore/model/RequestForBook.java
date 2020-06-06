package com.senla.bookstore.model;

public class RequestForBook {
    private int bookId;
    private RequestForBookStatus requestStatus;
    private int count;
    private int orderId;

    public RequestForBook(int bookId, RequestForBookStatus requestStatus,int count, int orderId) {
        this.bookId = bookId;
        this.requestStatus = requestStatus;
        this.count = count;
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public RequestForBookStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestForBookStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
