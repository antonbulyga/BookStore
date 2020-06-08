package com.senla.bookstore.model;

import com.senla.bookstore.service.Store;

import java.util.Arrays;

public class RequestForBookService {
    Store store = new Store();
    public RequestForBook[] addBookRequest(Store store, RequestForBook requestForBook){
        RequestForBook[] arrayOfRequests = store.getArrayOfRequestBooks();
        RequestForBook[] copyOfArray = Arrays.copyOf(arrayOfRequests, arrayOfRequests.length + 1);
        copyOfArray[copyOfArray.length - 1] = requestForBook;
        store.setArrayOfRequestBooks(copyOfArray);
        return copyOfArray;
    }

    public void sortRequestByCount(RequestForBook[] requestForBooks) {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        Arrays.sort(requestForBooks, requestForBookCountComparator);
        System.out.println("Array of requests sorted by number of requests per book: ");
        for (int i = 0; i < requestForBooks.length; i++) {
            System.out.println("Book id : " + requestForBooks[i].getBook() + " - " + requestForBooks[i].getBook().getRequestForBooks().length);
        }
    }

    public void requestSort(Store store){
        sortRequestByCount(store.getArrayOfRequestBooks());
        sortRequestByAlphabet(store.getArrayOfRequestBooks());
    }

    public void sortRequestByAlphabet(RequestForBook[] requestForBooks) {
        RequestForBookAlphabeticalComparator requestForBookCountComparator = new RequestForBookAlphabeticalComparator();
        Arrays.sort(requestForBooks, requestForBookCountComparator);
        System.out.println("Array of requests sorted by Alphabet: ");
        if(requestForBooks.length<0){
            System.out.println("You have no requests");
        }
        else {
            for (int i = 0; i < requestForBooks.length; i++) {
                System.out.println("Book id : " + requestForBooks[i].getBook() + " - " + requestForBooks[i].getBook().getTitle());
            }
        }

    }

    public void createRequestForBook(Book book, Order order){
        RequestForBookService requestForBookService = new RequestForBookService();
        RequestForBook[] requestForBookInBook = book.getRequestForBooks();
        RequestForBook requestForBook = new RequestForBook(book, RequestForBookStatus.ACTIVE, order);
        requestForBookInBook = requestForBookService.addBookRequest(store, requestForBook);
        book.setRequestForBooks(requestForBookInBook);
    }
}
