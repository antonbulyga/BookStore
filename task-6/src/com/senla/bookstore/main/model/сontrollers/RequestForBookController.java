package com.senla.bookstore.main.model.сontrollers;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.entity.RequestForBook;
import com.senla.bookstore.main.model.service.RequestForBookService;

import java.util.List;

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

    public RequestForBook createRequestForBook(Book book, Order order) {
       RequestForBook requestForBook = RequestForBookService.getInstance().createRequestForBook(book, order);
       return requestForBook;
    }
    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = RequestForBookService.getInstance().getListOfRequestForBook();
        return  requestForBookList;
    }
}
