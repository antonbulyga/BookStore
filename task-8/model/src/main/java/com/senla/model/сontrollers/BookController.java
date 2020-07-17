package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.service.BookServiceImpl;

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

    public void deleteBook(Book book){
        BookServiceImpl.getInstance().deleteBook(book);
    }

    public void showStaleBooks(){
        BookServiceImpl.getInstance().showStaleBooks();
    }

    public void arriveBookToStock(Book book){
        BookServiceImpl.getInstance().arriveBookToStock(book);
    }

    public void showUnsoldBooksMoreThanSixMonth(){
        BookServiceImpl.getInstance().showUnsoldBooksMoreThanSixMonth();
    }

    public void completingRequestAfterArrivingNewBook(Book book) {
        BookServiceImpl.getInstance().completingRequestAfterArrivingNewBook(book);
    }

    public List<Book> getListOfBooksInStoreHouse(){
       List<Book> books = BookServiceImpl.getInstance().getListOfBooksInStoreHouse();
        return books;
    }

    public void setListOfBooksInStoreHouse(List<Book> books){
        BookServiceImpl.getInstance().setListOfBooksInStoreHouse(books);
    }

    public Book createBook(int id, String title, String author, double price, LocalDate publicationDate){
       Book book = BookServiceImpl.getInstance().createBook(id, title, author, price, publicationDate);
       return book;
    }

    public Book getBookById(int id){
       Book book = BookServiceImpl.getInstance().getBookById(id);
       return book;
    }

    public void bookUpdate(Book book){
        BookServiceImpl.getInstance().bookUpdate(book);
    }

    public void showBooksInStock(){
       BookServiceImpl.getInstance().showBooksInStock();
   }

   public void sortBookByPrice(){
        BookServiceImpl.getInstance().sortBookByPrice();
   }

   public void addBookToListOfBooks(Book book){
        BookServiceImpl.getInstance().addBookToListOfBookInTheStorehouse(book);
   }

   public void sortBookByAuthor(){
        BookServiceImpl.getInstance().sortBookByPrice();
   }

   public void sortBookByDateArrive(){
        BookServiceImpl.getInstance().sortBookByDateArrive();
   }

   public void sortBookByAvailabilityInStock(){
        BookServiceImpl.getInstance().sortBookByAvailabilityInStock();
   }

   public void sortBookByPublicationDate(){
        BookServiceImpl.getInstance().sortBookByPublicationDate();
   }

}
