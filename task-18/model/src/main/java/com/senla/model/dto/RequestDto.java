package com.senla.model.dto;

import com.senla.model.enumeration.RequestForBookStatus;

public class RequestDto {
    private int id;
    private String titleOfBook;
    private String authorOfBook;
    private RequestForBookStatus requestStatus;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleOfBook() {
        return titleOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public void setAuthorOfBook(String authorOfBook) {
        this.authorOfBook = authorOfBook;
    }

    public RequestForBookStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestForBookStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
