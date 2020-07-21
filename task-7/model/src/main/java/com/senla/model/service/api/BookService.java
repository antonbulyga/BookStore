package main.java.com.senla.model.service.api;

import main.java.com.senla.model.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    Book createBook(int id, String title, String author, double price, LocalDate publicationDate);
    List<Book> getListOfBooksInStoreHouse();
    void addBookToListOfBookInTheStorehouse(Book book);
    void bookUpdate(Book book);
    List<Book> setStaleBookStatus();
    void showStaleBooks();
    int getCountOfMonthToMarkBookAsStale();
    void completingRequestAfterArrivingNewBook(Book book);
    void showUnsoldBooksMoreThanSixMonth();
    void arriveBookToStock(Book book);
    void setListOfBooksInStoreHouse(List<Book> books);
    void deleteBook(Book book);
    void showBooksInStock();
    void sortBookByPrice();
    void sortBookByAuthor();
    void sortBookByDateArrive();
    void sortBookByAvailabilityInStock();
    void sortBookByPublicationDate();
    Book getBookById(int id);
    void bookSort();
    void importBook();
    void exportBook();
}





