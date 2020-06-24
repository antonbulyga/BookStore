package com.senla.bookstore.main.model.сontrollers;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.service.BookService;

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

    public Book createBook(int id, String title, String author, double price, String bookStatus, String publicationDate){
       Book book = BookService.getInstance().createBook(id, title, author, price, bookStatus, publicationDate);
       return book;
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
