package com.senla.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.model.enumeration.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfOrder;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfDoneOrder;
    private List<BookDto> booksDto = new ArrayList<>();
    private double priceOfOrder;
    private OrderStatus orderStatus;
    private int customerId;
    private List<RequestDto> listOfRequestDto = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public LocalDate getDateOfDoneOrder() {
        return dateOfDoneOrder;
    }

    public void setDateOfDoneOrder(LocalDate dateOfDoneOrder) {
        this.dateOfDoneOrder = dateOfDoneOrder;
    }

    public List<BookDto> getBooksDto() {
        return booksDto;
    }

    public void setBooksDto(List<BookDto> booksDto) {
        this.booksDto = booksDto;
    }

    public double getPriceOfOrder() {
        return priceOfOrder;
    }

    public void setPriceOfOrder(double priceOfOrder) {
        this.priceOfOrder = priceOfOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<RequestDto> getListOfRequestDto() {
        return listOfRequestDto;
    }

    public void setListOfRequestDto(List<RequestDto> listOfRequestDto) {
        this.listOfRequestDto = listOfRequestDto;
    }
}
