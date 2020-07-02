package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.repository.BookRepository;
import main.java.com.senla.model.repository.RequestForBookRepository;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.сomparators.RequestForBookAlphabeticalComparator;
import main.java.com.senla.model.сomparators.RequestForBookCountComparator;
import main.java.com.senla.model.сomparators.RequestForBookStatus;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestForBookService {
    private static RequestForBookService instance;

    private RequestForBookService() {

    }

    public static RequestForBookService getInstance(){
        if(instance == null){
            instance = new RequestForBookService();
        }
        return instance;
    }

    public void closerRequestForBooksAfterArrivingBook(Book book){
        boolean ableToChange = getAbleToChangeRequestForBookStatusFromProperty();
        if(ableToChange == true){
            BookService.getInstance().arriveBookToStock(book);
            BookService.getInstance().completingRequestAfterArrivingNewBook(book);
        }
        else {
            BookService.getInstance().arriveBookToStock(book);
        }
    }

    public static boolean getAbleToChangeRequestForBookStatusFromProperty(){
        boolean ableToChange;
        String ableToChangeString = PropertyData.getProperty("ableOfChange");
        ableToChange = Boolean.parseBoolean(ableToChangeString);
        return ableToChange;
    }

    public void showListOfRequestsForBooks(){
        List<RequestForBook> listOfRequestForBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        System.out.println("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getBook().getTitle());
        }
    }

    public void sortRequestByCount() {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        List<RequestForBook> requestForBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
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
        List<RequestForBook> requestForBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
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
        RequestForBook requestForBook = RequestForBookRepository.getInstance().createRequestForBook(book, order);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        RequestForBookRepository.getInstance().addRequestForBookToList(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        RequestForBookRepository.getInstance().updateRequestForBook(requestForBook);
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        RequestForBookRepository.getInstance().deleteRequestForBook(requestForBook);
    }

    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        return requestForBookList;
    }

    public void setListOfRequestForBook(List<RequestForBook> requestForBooks){
       RequestForBookRepository.getInstance().setListOfRequestForBooks(requestForBooks);
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = RequestForBookRepository.getInstance().getRequestForBookById(id);
        return requestForBook;
    }
}
