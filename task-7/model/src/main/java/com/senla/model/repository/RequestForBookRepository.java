package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.RequestForBook;

import java.util.ArrayList;
import java.util.List;

public class RequestForBookRepository {
    private static RequestForBookRepository instance;
    private List<RequestForBook> listOfRequestForBooks = new ArrayList<>();

    private RequestForBookRepository(){

    }

    public static RequestForBookRepository getInstance(){
        if(instance == null){
            instance = new RequestForBookRepository();
        }
        return instance;
    }

    public List<RequestForBook> getListOfRequestForBooks() {
        return listOfRequestForBooks;
    }

    public void setListOfRequestForBooks(List<RequestForBook> listOfRequestForBooks) {
        this.listOfRequestForBooks = listOfRequestForBooks;
    }
}
