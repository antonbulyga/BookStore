package com.senla.model.service;

import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.ExportHelper;
import com.senla.model.сomparators.RequestForBookAlphabeticalComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class RequestForBookServiceImpl implements RequestForBookService {
    private RequestForBookRepository requestForBookRepository;
    private BookService bookService;
    private OrderService orderService;
    @Value("${ableOfChange}")
    private String flag;
    @Value("${requestForBookFile}")
    private String path;
    private static final Logger logger = Logger.getLogger(RequestForBookServiceImpl.class);

    @Autowired
    public void setRequestForBookRepository(RequestForBookRepository requestForBookRepository) {
        this.requestForBookRepository = requestForBookRepository;
    }
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional
    public RequestForBook read(Integer requestForBookId){
        RequestForBook requestForBook = requestForBookRepository.read(requestForBookId);
        int orderId = requestForBook.getOrder().getId();
        requestForBook.setOrder(orderService.read(orderId));
        return requestForBook;
    }


    @Transactional
    public List<RequestForBook> getAll(){
        List<RequestForBook> requestForBookList = requestForBookRepository.getAll();
        for (int i = 0; i < requestForBookList.size(); i++) {
            int orderId = requestForBookList.get(i).getOrder().getId();
            Order order = orderService.read(orderId);
            requestForBookList.get(i).setOrder(order);
        }
        return requestForBookList;
    }

    @Transactional
    public void importRequestForBook(){
        List<Order> orders = orderService.getListOfOrders();
        List<RequestForBook> requestForBooks = getListOfRequestForBook();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String bookTitle = strings[1];
                String bookAuthor = strings[2];
                String requestStatus = strings[3];
                int orderId = Integer.parseInt(strings[4]);
                RequestForBook requestForBook = new RequestForBook(id, bookTitle, bookAuthor, RequestForBookStatus.valueOf(requestStatus), orders.get(orderId));
                for (int i = 0; i < requestForBooks.size(); i++) {
                    if(requestForBook.getId() == requestForBooks.get(i).getId()){
                        requestForBookRepository.update(requestForBook);
                        count++;
                    }
                }
                if (count == 0) {
                    requestForBookRepository.create(requestForBook);
                }
                count = 0;

            }
        } catch (IOException | SQLException e) {
            logger.error("We have no file");
        }
    }

    public void exportRequestForBook(){
        List<RequestForBook> requestForBookList = getListOfRequestForBook();
        ExportHelper.write(null, null, null, requestForBookList, path);
    }

    public void closerRequestForBooksAfterArrivingBook(Book book){
        bookService.completingRequestAfterArrivingNewBook(book);
    }

    public void showListOfRequestsForBooks(){
        List<RequestForBook> listOfRequestForBooks = getAll();
        logger.error("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getTitleOfBook());
        }
    }

    public void requestSort(){
        sortRequestByAlphabet();
    }

    public List<RequestForBook> sortRequestByAlphabet() {
        RequestForBookAlphabeticalComparator requestForBookCountComparator = new RequestForBookAlphabeticalComparator();
        List<RequestForBook> requestForBooks = getAll();
        Collections.sort(requestForBooks, requestForBookCountComparator);
        logger.debug("Array of requests sorted by Alphabet: ");
        if(requestForBooks.size()<0){
            logger.error("You have no requests");
        }
        else {
            for (int i = 0; i < requestForBooks.size(); i++) {
                logger.debug(requestForBooks.get(i).getTitleOfBook());
            }
        }
        return requestForBooks;

    }

    public RequestForBook createRequestForBook(String bookTitle, String bookAuthor, Order order){
        RequestForBook requestForBook = new RequestForBook(bookTitle, bookAuthor, RequestForBookStatus.ACTIVE, order);
        try {
            requestForBookRepository.create(requestForBook);
            logger.info("Request has been created");
        } catch (SQLException e) {
            logger.error(e);
        }
        return requestForBook;
    }

    public void create(RequestForBook requestForBook){
        try {
            requestForBookRepository.create(requestForBook);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        try {
            requestForBookRepository.create(requestForBook);
        } catch (SQLException e) {
            logger.error(e);
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
        if (requestForBookList.isEmpty()) {
            throw new NoResultException("No requests in the database");
        }
        return requestForBookList;
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = read(id);
        if(requestForBook == null) {
            throw new NoResultException("No book with this ID");
        }
        return requestForBook;
    }

    public void update(RequestForBook requestForBook){
        requestForBookRepository.update(requestForBook);
    }
}
