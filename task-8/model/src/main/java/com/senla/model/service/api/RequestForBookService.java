package main.java.com.senla.model.service.api;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;

import java.util.List;

public interface RequestForBookService {
    void closerRequestForBooksAfterArrivingBook(Book book);

    void showListOfRequestsForBooks();

    void sortRequestByCount();

    void requestSort();

    void sortRequestByAlphabet();

    RequestForBook createRequestForBook(Book book, Order order);

    void addRequestForBookToList(RequestForBook requestForBook);

    void updateRequestForBook(RequestForBook requestForBook);

    void deleteRequestForBook(RequestForBook requestForBook);

    List<RequestForBook> getListOfRequestForBook();

    RequestForBook getRequestForBookById(int id);

    void importRequestForBook();

    void exportRequestForBook();

}
