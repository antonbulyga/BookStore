package com.senla.bookstore.main.model.service;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.сomparators.RequestForBookAlphabeticalComparator;
import com.senla.bookstore.main.model.сomparators.RequestForBookCountComparator;
import com.senla.bookstore.main.model.сomparators.RequestForBookStatus;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.entity.RequestForBook;
import com.senla.bookstore.main.model.entity.Store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestForBookService {
    private static RequestForBookService instance;
    private Store store;

    private RequestForBookService() {
        store = StoreService.getInstance().getStore();
    }

    public static RequestForBookService getInstance(){
        if(instance == null){
            instance = new RequestForBookService();
        }
        return instance;
    }

    public void showListOfRequestsForBooks(){
        List<RequestForBook> listOfRequestForBooks = store.getListOfRequestBooks();
        System.out.println("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getBook().getTitle());
        }
    }

    public void sortRequestByCount() {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        List<RequestForBook> requestForBooks = store.getListOfRequestBooks();
        Collections.sort(requestForBooks, requestForBookCountComparator);
        System.out.println("Array of requests sorted by number of requests per book: ");
        for (int i = 0; i < requestForBooks.size(); i++) {
            System.out.println(requestForBooks.get(i).getBook().getTitle() + " - " + requestForBooks.get(i).getBook().getRequestForBooks().size());
        }
    }

    public void requestSort(){
        sortRequestByCount();
        sortRequestByAlphabet();
    }

    public void sortRequestByAlphabet() {
        RequestForBookAlphabeticalComparator requestForBookCountComparator = new RequestForBookAlphabeticalComparator();
        List<RequestForBook> requestForBooks = store.getListOfRequestBooks();
        Collections.sort(requestForBooks, requestForBookCountComparator);
        System.out.println("Array of requests sorted by Alphabet: ");
        if(requestForBooks.size()<0){
            System.out.println("You have no requests");
        }
        else {
            for (int i = 0; i < requestForBooks.size(); i++) {
                System.out.println(requestForBooks.get(i).getBook().getAuthor() + " - " + requestForBooks.get(i).getBook().getTitle());
            }
        }

    }

    public RequestForBook createRequestForBook(Book book, Order order){
        List<RequestForBook> requestForBookInBook = new ArrayList<>();
        int id;
        List<RequestForBook> lisOfRequestsForBook = store.getListOfRequestBooks();
        if(lisOfRequestsForBook == null){
            id = 0;
        }
        else {
            id = lisOfRequestsForBook.size() + 1;
        }
        RequestForBook requestForBook = new RequestForBook(id, book, RequestForBookStatus.ACTIVE, order);
        requestForBookInBook.add(requestForBook);
        book.setRequestForBooks(requestForBookInBook);
        order.setArrayOfRequestForBooks(requestForBookInBook);
        return requestForBook;
    }

    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = store.getListOfRequestBooks();
        return requestForBookList;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
