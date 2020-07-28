package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;

import java.util.List;

public interface RequestForBookRepository {
    RequestForBook createRequestForBook(Book book, Order order);

    void addRequestForBookToList(RequestForBook requestForBook);

    void updateRequestForBook(RequestForBook requestForBook);

    void deleteRequestForBook(RequestForBook requestForBook);

    RequestForBook getRequestForBookById(int id);

    List<RequestForBook> getListOfRequestForBooks();

    void setListOfRequestForBooks(List<RequestForBook> listOfRequestForBooks);

}
