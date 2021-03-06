package com.senla.model.service.api;

import com.senla.model.entity.Book;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    Book createBook(Book book) throws SQLException;
    List<Book> getListOfBooksInStoreHouse();
    void addBookToListOfBookInTheStorehouse(Book book) throws SQLException;
    void bookUpdate(Book book);
    void completingRequestAfterArrivingNewBook(Book book);
    List<Book> showUnsoldBooksMoreThanSixMonth();
    void deleteBook(Book book);
    void showBooksInStock();
    List<Book> sortBookByPrice();
    List<Book> sortBookByAuthor();
    List<Book> sortBookByDateArrive();
    List<Book> sortBookByPublicationDate();
    Book getBookById(int id);
    void importBook();
    void exportBook();
    List<Book> customSearch(String author, LocalDate endDate);
    boolean bookInStockChecker(String titleBook, String authorBook);
    Book getBookByAuthorAndTitle(String titleBook, String authorBook);
}





