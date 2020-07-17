package main.java.com.senla.model.service;

import annotation.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.repository.RequestForBookRepositoryImpl;
import main.java.com.senla.model.service.api.RequestForBookService;
import main.java.com.senla.model.сomparators.RequestForBookAlphabeticalComparator;
import main.java.com.senla.model.сomparators.RequestForBookCountComparator;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;

import java.util.Collections;
import java.util.List;

public class RequestForBookServiceImpl implements RequestForBookService {
    private static RequestForBookServiceImpl instance;

    private RequestForBookServiceImpl() {

    }

    public static RequestForBookServiceImpl getInstance(){
        if(instance == null){
            instance = new RequestForBookServiceImpl();
        }
        return instance;
    }

    public void closerRequestForBooksAfterArrivingBook(Book book){
        boolean ableToChange = getAbleToChangeRequestForBookStatusFromProperty();
        if(ableToChange == true){
            BookServiceImpl.getInstance().arriveBookToStock(book);
            BookServiceImpl.getInstance().completingRequestAfterArrivingNewBook(book);
        }
        else {
            BookServiceImpl.getInstance().arriveBookToStock(book);
        }
    }

    public static boolean getAbleToChangeRequestForBookStatusFromProperty(){
        boolean ableToChange;
        @MyInject(key = "ableOfChange")
        String flag = null;

        ableToChange = Boolean.parseBoolean(flag);
        return ableToChange;
    }

    public void showListOfRequestsForBooks(){
        List<RequestForBook> listOfRequestForBooks = RequestForBookRepositoryImpl.getInstance().getListOfRequestForBooks();
        System.out.println("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getBook().getTitle());
        }
    }

    public void sortRequestByCount() {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        List<RequestForBook> requestForBooks = RequestForBookRepositoryImpl.getInstance().getListOfRequestForBooks();
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
        List<RequestForBook> requestForBooks = RequestForBookRepositoryImpl.getInstance().getListOfRequestForBooks();
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
        RequestForBook requestForBook = RequestForBookRepositoryImpl.getInstance().createRequestForBook(book, order);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        RequestForBookRepositoryImpl.getInstance().addRequestForBookToList(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        RequestForBookRepositoryImpl.getInstance().updateRequestForBook(requestForBook);
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        RequestForBookRepositoryImpl.getInstance().deleteRequestForBook(requestForBook);
    }

    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = RequestForBookRepositoryImpl.getInstance().getListOfRequestForBooks();
        return requestForBookList;
    }

    public void setListOfRequestForBook(List<RequestForBook> requestForBooks){
       RequestForBookRepositoryImpl.getInstance().setListOfRequestForBooks(requestForBooks);
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = RequestForBookRepositoryImpl.getInstance().getRequestForBookById(id);
        return requestForBook;
    }
}
