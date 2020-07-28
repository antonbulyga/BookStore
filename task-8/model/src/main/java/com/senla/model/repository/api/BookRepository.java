package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.entity.Book;
import java.time.LocalDate;
import java.util.List;


public interface BookRepository {
    Book createBook(int id, String title, String author, double price, LocalDate publicationDate);

    void addBookToListOfBookInTheStorehouse(Book book);

    void bookUpdate(Book book);

    void deleteBook(Book book);

    Book getBookById(int id);

    void setListOfBooksInStorehouse(List<Book> listOfBooksInStorehouse);

    List<Book> getListOfBooksInStorehouse();

}
