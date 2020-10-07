package com.senla.model.service.api;

import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;

import java.util.List;

public interface RequestForBookService {
    RequestForBook read(Integer requestForBookId);

    List<RequestForBook> getAll();

    void closerRequestForBooksAfterArrivingBook(Book book);

    void showListOfRequestsForBooks();

    void requestSort();

    List<RequestForBook> sortRequestByAlphabet();

    RequestForBook createRequestForBook(String bookTitle, String bookAuthor, Order order);

    void addRequestForBookToList(RequestForBook requestForBook);

    void updateRequestForBook(RequestForBook requestForBook);

    void deleteRequestForBook(RequestForBook requestForBook);

    List<RequestForBook> getListOfRequestForBook();

    RequestForBook getRequestForBookById(int id);

    void importRequestForBook();

    void exportRequestForBook();

    void create(RequestForBook requestForBook);

}
