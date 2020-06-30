package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> listOfBooksInStorehouse = new ArrayList<>();

    private BookRepository(){

    }

    public static BookRepository getInstance(){
        if(instance == null){
            instance = new BookRepository();
        }
        return instance;
    }

    public List<Book> getListOfBooksInStorehouse() {
        return listOfBooksInStorehouse;
    }

    public void setListOfBooksInStorehouse(List<Book> listOfBooksInStorehouse) {
        this.listOfBooksInStorehouse = listOfBooksInStorehouse;
    }
}
