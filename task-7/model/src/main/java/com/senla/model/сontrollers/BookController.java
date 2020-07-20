package main.java.com.senla.model.сontrollers;

import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.service.api.BookService;

import java.time.LocalDate;
import java.util.List;

public class BookController {
    private static BookController instance;
    @MyAutoWired
    private BookService bookService;

    private BookController(){

    }

    public static BookController getInstance(){
        if(instance == null){
            instance = new BookController();
        }
        return instance;
    }

    public void deleteBook(Book book){
       bookService.deleteBook(book);
    }

    public void showStaleBooks(){
        bookService.showStaleBooks();
    }

    public void arriveBookToStock(Book book){
        bookService.arriveBookToStock(book);
    }

    public void showUnsoldBooksMoreThanSixMonth(){
        bookService.showUnsoldBooksMoreThanSixMonth();
    }

    public void completingRequestAfterArrivingNewBook(Book book) {
        bookService.completingRequestAfterArrivingNewBook(book);
    }

    public List<Book> getListOfBooksInStoreHouse(){
       List<Book> books = bookService.getListOfBooksInStoreHouse();
        return books;
    }

    public void setListOfBooksInStoreHouse(List<Book> books){
        bookService.setListOfBooksInStoreHouse(books);
    }

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
       Book book = bookService.createBook(id, title, author, price, publicationDate);
       return book;
    }

    public Book getBookById(int id){
       Book book = bookService.getBookById(id);
       return book;
    }

    public void bookUpdate(Book book){
        bookService.bookUpdate(book);
    }

    public void showBooksInStock(){
       bookService.showBooksInStock();
   }

   public void sortBookByPrice(){
        bookService.sortBookByPrice();
   }

   public void addBookToListOfBooks(Book book){
        bookService.addBookToListOfBookInTheStorehouse(book);
   }

   public void sortBookByAuthor(){
        bookService.sortBookByPrice();
   }

   public void sortBookByDateArrive(){
        bookService.sortBookByDateArrive();
   }

   public void sortBookByAvailabilityInStock(){
        bookService.sortBookByAvailabilityInStock();
   }

   public void sortBookByPublicationDate(){
        bookService.sortBookByPublicationDate();
   }

}
