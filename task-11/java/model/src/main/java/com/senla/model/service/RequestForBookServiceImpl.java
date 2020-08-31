package com.senla.model.service;

import com.senla.config.annotations.Component;
import com.senla.config.annotations.MyAutoWired;
import com.senla.config.annotations.MyInject;
import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.repository.api.OrderRepository;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.ExportHelper;
import com.senla.model.utils.generators.RequestForBookIdGenerator;
import com.senla.model.сomparators.RequestForBookAlphabeticalComparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
@Component
public class RequestForBookServiceImpl implements RequestForBookService {
    @MyAutoWired
    private RequestForBookRepository requestForBookRepository;
    @MyAutoWired
    private BookService bookService;
    @MyAutoWired
    private OrderService orderService;
    @MyInject(key = "ableOfChange")
    private String flag;
    @MyInject(key = "requestForBookFile")
    private String path;
    @MyAutoWired
    private OrderRepository orderRepository;

    public RequestForBook read(Integer requestForBookId){
        RequestForBook requestForBook = requestForBookRepository.read(requestForBookId);
        int orderId = requestForBook.getOrder().getId();
        requestForBook.setOrder(orderRepository.read(orderId));
        return requestForBook;
    }

    public List<RequestForBook> getAll(){
        List<RequestForBook> requestForBookList = requestForBookRepository.getAll();
        for (int i = 0; i < requestForBookList.size(); i++) {
            int orderId = requestForBookList.get(i).getOrder().getId();
            Order order = orderRepository.read(orderId);
            requestForBookList.get(i).setOrder(order);
        }
        return requestForBookList;
    }

    public void importRequestForBook(){
        List<Order> orders = orderService.getListOfOrders();
        List<RequestForBook> requestForBooks = getListOfRequestForBook();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String bookTitle = strings[1];
                String bookAuthor = strings[2];
                int orderId = Integer.parseInt(strings[3]);
                RequestForBook requestForBook = createRequestForBook(bookTitle, bookAuthor, orders.get(orderId));
                for (int i = 0; i < requestForBooks.size(); i++) {
                    if(requestForBook.getId() == requestForBooks.get(i).getId()){
                        updateRequestForBook(requestForBook);
                    }
                    else {
                        addRequestForBookToList(requestForBook);
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }

    public void exportRequestForBook(){
        List<RequestForBook> requestForBookList = getListOfRequestForBook();
        ExportHelper.write(null, null, null, requestForBookList, path);
    }

    public void closerRequestForBooksAfterArrivingBook(Book book){
        bookService.completingRequestAfterArrivingNewBook(book);
    }

    public boolean getAbleToChangeRequestForBookStatusFromProperty(){
        boolean ableToChange;
        ableToChange = Boolean.parseBoolean(flag);
        return ableToChange;
    }

    public void showListOfRequestsForBooks(){
        List<RequestForBook> listOfRequestForBooks = getAll();
        System.out.println("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getTitleOfBook());
        }
    }


    public void requestSort(){
        sortRequestByAlphabet();
    }

    public void sortRequestByAlphabet() {
        RequestForBookAlphabeticalComparator requestForBookCountComparator = new RequestForBookAlphabeticalComparator();
        List<RequestForBook> requestForBooks = getAll();
        Collections.sort(requestForBooks, requestForBookCountComparator);
        System.out.println("Array of requests sorted by Alphabet: ");
        if(requestForBooks.size()<0){
            System.out.println("You have no requests");
        }
        else {
            for (int i = 0; i < requestForBooks.size(); i++) {
                System.out.println(requestForBooks.get(i).getTitleOfBook());
            }
        }

    }

    public RequestForBook createRequestForBook(String bookTitle, String bookAuthor, Order order){
        RequestForBook requestForBook = new RequestForBook(RequestForBookIdGenerator.getRequestForBookId(), bookTitle, bookAuthor, RequestForBookStatus.ACTIVE, order);
        try {
            requestForBookRepository.create(requestForBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestForBook;
    }

    public void create(RequestForBook requestForBook){
        try {
            requestForBookRepository.create(requestForBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        try {
            requestForBookRepository.create(requestForBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        requestForBookRepository.update(requestForBook);
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        requestForBookRepository.delete(requestForBook);
    }

    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = getAll();
        return requestForBookList;
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = read(id);
        return requestForBook;
    }
}
