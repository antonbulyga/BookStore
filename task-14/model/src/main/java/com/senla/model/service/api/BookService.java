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
    void showUnsoldBooksMoreThanSixMonth();
    void deleteBook(Book book);
    void showBooksInStock();
    void sortBookByPrice();
    void sortBookByAuthor();
    void sortBookByDateArrive();
    void sortBookByPublicationDate();
    Book getBookById(int id);
    void importBook();
    void exportBook();
    void customSearch(String author, LocalDate endDate);
    boolean bookInStockChecker(String titleBook, String authorBook);
    Book getBookByAuthorAndTitle(String titleBook, String authorBook);
}





