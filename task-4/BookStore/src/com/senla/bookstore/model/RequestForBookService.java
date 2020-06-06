package com.senla.bookstore.model;

import java.util.Arrays;

public class RequestForBookService {
    public RequestForBook[] addBookRequest(RequestForBook[] arrayOfRequests, RequestForBook requestForBook){
        RequestForBook[] copyOfArray = Arrays.copyOf(arrayOfRequests, arrayOfRequests.length + 1);
        copyOfArray[copyOfArray.length - 1] = requestForBook;
        return copyOfArray;
    }

    public void sortRequestByCount(RequestForBook[] requestForBooks) {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        Arrays.sort(requestForBooks, requestForBookCountComparator);
        System.out.println("Array of requests sorted by number of requests per book: ");
        for (int i = 0; i < requestForBooks.length; i++) {
            System.out.println("Book id : " + requestForBooks[i].getBookId() + " - " + requestForBooks[i].getCount());
        }
    }

    public RequestForBook[] createRequestForBook(Book book, RequestForBook[] requestForBooks){
        RequestForBookService requestForBookService = new RequestForBookService();
        int bookId = book.getId();
        int count = book.getStockLevel().count;

        RequestForBook requestForBook = new RequestForBook(bookId, RequestForBookStatus.ACTIVE, 0, count++ );
        requestForBooks = requestForBookService.addBookRequest(requestForBooks, requestForBook);
        return requestForBooks;
    }
}
