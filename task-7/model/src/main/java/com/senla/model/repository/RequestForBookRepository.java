package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.сomparators.RequestForBookStatus;

import java.util.ArrayList;
import java.util.List;

public class RequestForBookRepository {
    private static RequestForBookRepository instance;
    private List<RequestForBook> listOfRequestForBooks = new ArrayList<>();

    private RequestForBookRepository(){

    }

    public static RequestForBookRepository getInstance(){
        if(instance == null){
            instance = new RequestForBookRepository();
        }
        return instance;
    }

    public RequestForBook createRequestForBook(Book book, Order order){
        RequestForBook requestForBook = new RequestForBook(RequestForBookIdGenerator.getRequestForBookId(), book, RequestForBookStatus.ACTIVE, order);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        List<RequestForBook> requestForBookInBook = new ArrayList<>();
        List<RequestForBook> lisOfRequestsForBook = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        requestForBookInBook.add(requestForBook);
        requestForBook.getBook().setRequestForBooks(requestForBookInBook);
        requestForBook.getOrder().setArrayOfRequestForBooks(requestForBookInBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        List<RequestForBook> lisOfRequestsForBook = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        for (int i = 0; i < lisOfRequestsForBook.size(); i++) {
            if(requestForBook.getId() == lisOfRequestsForBook.get(i).getId()){
                deleteRequestForBook(lisOfRequestsForBook.get(i));
                lisOfRequestsForBook.set(i, requestForBook);
                RequestForBookRepository.getInstance().setListOfRequestForBooks(lisOfRequestsForBook);
            }
        }
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        List<RequestForBook> lisOfRequestsForBook = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        for (int i = 0; i < lisOfRequestsForBook.size(); i++) {
            if(requestForBook.getId() == lisOfRequestsForBook.get(i).getId()){
                lisOfRequestsForBook.remove(lisOfRequestsForBook.get(i));
            }
        }
        RequestForBookRepository.getInstance().setListOfRequestForBooks(lisOfRequestsForBook);
    }

    public RequestForBook getRequestForBookById(int id){
        List<RequestForBook> requestForBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        RequestForBook requestForBook = null;
        for (int i = 0; i < requestForBooks.size(); i++) {
            if(requestForBooks.get(i).getId() == id){
                requestForBook = requestForBooks.get(i);
            }
        }
        return requestForBook;
    }

    public List<RequestForBook> getListOfRequestForBooks() {
        return listOfRequestForBooks;
    }

    public void setListOfRequestForBooks(List<RequestForBook> listOfRequestForBooks) {
        this.listOfRequestForBooks = listOfRequestForBooks;
    }
}
