package com.senla.model.сontrollers;

import com.senla.model.entity.Book;
import com.senla.model.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    public void exportBook(){
        bookService.exportBook();
    }

    public void customSearch(String author, LocalDate endDate){
        bookService.customSearch(author, endDate);
    }

    public void importBook(){
        bookService.importBook();
    }

    public void deleteBook(Book book){
       bookService.deleteBook(book);
    }

    public boolean bookInStockChecker(String titleBook, String authorBook){
        boolean flag = bookService.bookInStockChecker(titleBook, authorBook);
        return flag;
    }

    public Book getBookByAuthorAndTitle(String titleBook, String authorBook){
        Book book = bookService.getBookByAuthorAndTitle(titleBook, authorBook);
        return book;
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

    public Book createBook(Book book) throws SQLException {
       bookService.createBook(book);
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

   public void sortBookByAuthor(){
        bookService.sortBookByPrice();
   }

   public void sortBookByDateArrive(){
        bookService.sortBookByDateArrive();
   }

   public void sortBookByPublicationDate(){
        bookService.sortBookByPublicationDate();
   }

}
