package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.service.BookService;

import java.time.LocalDate;
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

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
       Book book = BookService.getInstance().createBook(id, title, author, price, publicationDate);
       return book;
    }

    public Book getBookById(int id){
       Book book = BookService.getInstance().getBookById(id);
       return book;
    }

    public void bookUpdate(Book book){
        BookService.getInstance().bookUpdate(book);
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
