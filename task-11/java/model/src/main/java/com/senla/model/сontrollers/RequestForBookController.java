package main.java.com.senla.model.сontrollers;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.service.api.RequestForBookService;
import main.java.com.senla.model.utils.ExportHelper;

import java.util.List;

public class RequestForBookController {
    private static RequestForBookController instance;
    @MyAutoWired
    private RequestForBookService requestForBookService;

    private RequestForBookController(){

    }

    public void importRequestForBook(){
        requestForBookService.importRequestForBook();
    }

    public void exportRequestForBook(){
        requestForBookService.exportRequestForBook();
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

    public void sortRequestByAlphabet(){
        requestForBookService.sortRequestByAlphabet();
    }

    public RequestForBook createRequestForBook(String bookTitle, String bookAuthor, Order order) {
       RequestForBook requestForBook = requestForBookService.createRequestForBook(bookTitle,bookAuthor, order);
       return requestForBook;
    }
    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = requestForBookService.getListOfRequestForBook();
        return  requestForBookList;
    }

    public void create(RequestForBook requestForBook){
        requestForBookService.create(requestForBook);
    }

}
