package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.service.RequestForBookService;

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

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = RequestForBookService.getInstance().getRequestForBookById(id);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        RequestForBookService.getInstance().addRequestForBookToList(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        RequestForBookService.getInstance().updateRequestForBook(requestForBook);
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

    public void setListOfRequestForBook(List<RequestForBook> requestForBooks){
        RequestForBookService.getInstance().setListOfRequestForBook(requestForBooks);
    }
}
