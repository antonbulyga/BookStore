package com.senla.bookstore.model.сontrollers;

import com.senla.bookstore.model.entity.Book;
import com.senla.bookstore.model.entity.Order;
import com.senla.bookstore.model.service.RequestForBookService;

public class RequestForBookController {
    private static RequestForBookController instance;

    private RequestForBookController(){

    }

    public static  RequestForBookController getInstance() {
        if(instance == null){
            instance = new RequestForBookController();
        }
        return instance;
    }

    public void showListOfRequestsForBooks() {
        RequestForBookService.getInstance().showListOfRequestsForBooks();
    }

    public void sortRequestByCount(){
        RequestForBookService.getInstance().sortRequestByCount();
    }

    public void sortRequestByAlphabet(){
        RequestForBookService.getInstance().sortRequestByCount();
    }

    public void createRequestForBook(Book book, Order order) {
        RequestForBookService.getInstance().createRequestForBook(book, order);
    }
}
