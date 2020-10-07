package com.senla.model.entity;

import com.senla.model.enumeration.RequestForBookStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request_for_book")
public class RequestForBook implements Serializable {
    private static final long serialVersionUID = -4009735170071392702L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title", nullable = false)
    private String titleOfBook;
    @Column(name = "author", nullable = false)
    private String authorOfBook;
    @Enumerated(EnumType.STRING)
    @Column(name = "request_for_book_status")
    private RequestForBookStatus requestStatus;
    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public RequestForBook(int id, String titleOfBook, String authorOfBook, RequestForBookStatus requestStatus, Order order) {
        this.id = id;
        this.titleOfBook = titleOfBook;
        this.authorOfBook = authorOfBook;
        this.requestStatus = requestStatus;
        this.order = order;

    }
    public RequestForBook(String titleOfBook, String authorOfBook, RequestForBookStatus requestStatus, Order order) {
        this.titleOfBook = titleOfBook;
        this.authorOfBook = authorOfBook;
        this.requestStatus = requestStatus;
        this.order = order;
    }


    public RequestForBook(){

    }


    public String getTitleOfBook() {
        return titleOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
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

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public void setAuthorOfBook(String authorOfBook) {
        this.authorOfBook = authorOfBook;
    }
}
