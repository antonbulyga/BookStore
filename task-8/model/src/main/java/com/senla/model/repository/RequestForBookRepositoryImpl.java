package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.repository.api.RequestForBookRepository;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.сomparators.RequestForBookStatus;

import java.util.ArrayList;
import java.util.List;
@Component
public class RequestForBookRepositoryImpl implements RequestForBookRepository {
    private List<RequestForBook> listOfRequestForBooks = new ArrayList<>();


    public RequestForBook createRequestForBook(Book book, Order order){
        RequestForBook requestForBook = new RequestForBook(RequestForBookIdGenerator.getRequestForBookId(), book, RequestForBookStatus.ACTIVE, order);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        List<RequestForBook> requestForBookInBook = new ArrayList<>();
        List<RequestForBook> lisOfRequestsForBook = getListOfRequestForBooks();
        requestForBookInBook.add(requestForBook);
        requestForBook.getBook().setRequestForBooks(requestForBookInBook);
        requestForBook.getOrder().setArrayOfRequestForBooks(requestForBookInBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        List<RequestForBook> lisOfRequestsForBook = getListOfRequestForBooks();
        for (int i = 0; i < lisOfRequestsForBook.size(); i++) {
            if(requestForBook.getId() == lisOfRequestsForBook.get(i).getId()){
                deleteRequestForBook(lisOfRequestsForBook.get(i));
                lisOfRequestsForBook.set(i, requestForBook);
                setListOfRequestForBooks(lisOfRequestsForBook);
            }
        }
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        List<RequestForBook> lisOfRequestsForBook = getListOfRequestForBooks();
        for (int i = 0; i < lisOfRequestsForBook.size(); i++) {
            if(requestForBook.getId() == lisOfRequestsForBook.get(i).getId()){
                lisOfRequestsForBook.remove(lisOfRequestsForBook.get(i));
            }
        }
        setListOfRequestForBooks(lisOfRequestsForBook);
    }

    public RequestForBook getRequestForBookById(int id){
        List<RequestForBook> requestForBooks = getListOfRequestForBooks();
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
