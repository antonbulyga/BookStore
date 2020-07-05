package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.service.RequestForBookServiceImpl;

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

    public void closerRequestForBooksAfterArrivingBook(Book book){
        RequestForBookServiceImpl.getInstance().closerRequestForBooksAfterArrivingBook(book);
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = RequestForBookServiceImpl.getInstance().getRequestForBookById(id);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        RequestForBookServiceImpl.getInstance().addRequestForBookToList(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        RequestForBookServiceImpl.getInstance().updateRequestForBook(requestForBook);
    }

    public void showListOfRequestsForBooks() {
        RequestForBookServiceImpl.getInstance().showListOfRequestsForBooks();
    }

    public void sortRequestByCount(){
        RequestForBookServiceImpl.getInstance().sortRequestByCount();
    }

    public void sortRequestByAlphabet(){
        RequestForBookServiceImpl.getInstance().sortRequestByCount();
    }

    public RequestForBook createRequestForBook(Book book, Order order) {
       RequestForBook requestForBook = RequestForBookServiceImpl.getInstance().createRequestForBook(book, order);
       return requestForBook;
    }
    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = RequestForBookServiceImpl.getInstance().getListOfRequestForBook();
        return  requestForBookList;
    }

    public void setListOfRequestForBook(List<RequestForBook> requestForBooks){
        RequestForBookServiceImpl.getInstance().setListOfRequestForBook(requestForBooks);
    }
}
