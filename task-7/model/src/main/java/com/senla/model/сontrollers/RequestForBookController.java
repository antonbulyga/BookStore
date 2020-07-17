package main.java.com.senla.model.сontrollers;

import annotation.MyAutoWired;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.service.RequestForBookServiceImpl;
import main.java.com.senla.model.service.api.RequestForBookService;

import java.util.List;

public class RequestForBookController {
    private static RequestForBookController instance;
    @MyAutoWired
    private RequestForBookService requestForBookService;

    private RequestForBookController(){

    }

    public static  RequestForBookController getInstance() {
        if(instance == null){
            instance = new RequestForBookController();
        }
        return instance;
    }

    public void closerRequestForBooksAfterArrivingBook(Book book){
        requestForBookService.closerRequestForBooksAfterArrivingBook(book);
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = requestForBookService.getRequestForBookById(id);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        requestForBookService.addRequestForBookToList(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        requestForBookService.updateRequestForBook(requestForBook);
    }

    public void showListOfRequestsForBooks() {
        requestForBookService.showListOfRequestsForBooks();
    }

    public void sortRequestByCount(){
        requestForBookService.sortRequestByCount();
    }

    public void sortRequestByAlphabet(){
        requestForBookService.sortRequestByCount();
    }

    public RequestForBook createRequestForBook(Book book, Order order) {
       RequestForBook requestForBook = requestForBookService.createRequestForBook(book, order);
       return requestForBook;
    }
    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = requestForBookService.getListOfRequestForBook();
        return  requestForBookList;
    }

    public void setListOfRequestForBook(List<RequestForBook> requestForBooks){
        requestForBookService.setListOfRequestForBook(requestForBooks);
    }
}
