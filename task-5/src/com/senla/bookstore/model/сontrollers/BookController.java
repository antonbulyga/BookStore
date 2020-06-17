package com.senla.bookstore.model.сontrollers;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.service.BookService;

import java.util.List;

public class BookController {
    private static BookController instance;

    private BookController(){

    }

    public static BookController getInstance(){
        if(instance == null){
            instance = new BookController();
        }
        return instance;
    }

    public List<Book> getListOfBooksInStoreHouse(){
       List<Book> books = BookService.getInstance().getListOfBooksInStoreHouse();
        return books;
    }


    public void showBooksInStock(){
       BookService.getInstance().showBooksInStock();
   }

   public void sortBookByPrice(){
        BookService.getInstance().sortBookByPrice();
   }

   public void addBookToListOfBooks(Book book){
        BookService.getInstance().addBookToListOfBooks(book);
   }

   public void sortBookByAuthor(){
        BookService.getInstance().sortBookByPrice();
   }

   public void sortBookByDateArrive(){
        BookService.getInstance().sortBookByDateArrive();
   }

   public void sortBookByAvailabilityInStock(){
        BookService.getInstance().sortBookByAvailabilityInStock();
   }

   public void sortBookByPublicationDate(){
        BookService.getInstance().sortBookByPublicationDate();
   }

}
