package main.java.com.senla.model.service;

import annotation.Component;

import annotation.MyAutoWired;
import annotation.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.repository.api.RequestForBookRepository;
import main.java.com.senla.model.service.api.BookService;
import main.java.com.senla.model.service.api.RequestForBookService;
import main.java.com.senla.model.сomparators.RequestForBookAlphabeticalComparator;
import main.java.com.senla.model.сomparators.RequestForBookCountComparator;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;

import java.util.Collections;
import java.util.List;

@Component
public class RequestForBookServiceImpl implements RequestForBookService {
    @MyAutoWired
    private RequestForBookRepository requestForBookRepository;
    @MyAutoWired
    private BookService bookService;
    @MyInject(key = "ableOfChange")
    private static String flag;


    public void closerRequestForBooksAfterArrivingBook(Book book){
        boolean ableToChange = getAbleToChangeRequestForBookStatusFromProperty();
        if(ableToChange == true){
            bookService.arriveBookToStock(book);
            bookService.completingRequestAfterArrivingNewBook(book);
        }
        else {
            bookService.arriveBookToStock(book);
        }
    }

    public boolean getAbleToChangeRequestForBookStatusFromProperty(){
        boolean ableToChange;
        ableToChange = Boolean.parseBoolean(flag);
        return ableToChange;
    }

    public void showListOfRequestsForBooks(){
        List<RequestForBook> listOfRequestForBooks = requestForBookRepository.getListOfRequestForBooks();
        System.out.println("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getBook().getTitle());
        }
    }

    public void sortRequestByCount() {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        List<RequestForBook> requestForBooks = requestForBookRepository.getListOfRequestForBooks();
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
        List<RequestForBook> requestForBooks = requestForBookRepository.getListOfRequestForBooks();
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
        RequestForBook requestForBook = requestForBookRepository.createRequestForBook(book, order);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        requestForBookRepository.addRequestForBookToList(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        requestForBookRepository.updateRequestForBook(requestForBook);
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        requestForBookRepository.deleteRequestForBook(requestForBook);
    }

    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = requestForBookRepository.getListOfRequestForBooks();
        return requestForBookList;
    }

    public void setListOfRequestForBook(List<RequestForBook> requestForBooks){
       requestForBookRepository.setListOfRequestForBooks(requestForBooks);
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = requestForBookRepository.getRequestForBookById(id);
        return requestForBook;
    }
}
