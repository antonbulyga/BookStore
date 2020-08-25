package main.java.com.senla.model.service;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.RequestForBookStatus;
import main.java.com.senla.model.repository.RequestForBookRepositoryImpl;
import main.java.com.senla.model.repository.api.RequestForBookRepository;
import main.java.com.senla.model.service.api.BookService;
import main.java.com.senla.model.service.api.OrderService;
import main.java.com.senla.model.service.api.RequestForBookService;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.сomparators.RequestForBookAlphabeticalComparator;
import main.java.com.senla.model.сomparators.RequestForBookCountComparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Component
public class RequestForBookServiceImpl implements RequestForBookService {
    @MyAutoWired
    private BookService bookService;
    @MyAutoWired
    private OrderService orderService;
    @MyInject(key = "ableOfChange")
    private String flag;
    @MyInject(key = "requestForBookFile")
    private String path;

    public void importRequestForBook(){
        List<Order> orders = orderService.getListOfOrders();
        List<Book> books = bookService.getListOfBooksInStoreHouse();
        List<RequestForBook> requestForBooks = getListOfRequestForBook();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                int bookId = Integer.parseInt(strings[1]);
                String requestForBookStatus = strings[2];
                int orderId = Integer.parseInt(strings[3]);
                RequestForBook requestForBook = createRequestForBook(books.get(bookId),orders.get(orderId));
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
        List<RequestForBook> listOfRequestForBooks = RequestForBookRepositoryImpl.getInstance().getAll();
        System.out.println("List of Request for books :");
        for (int i = 0; i < listOfRequestForBooks.size(); i++) {
             System.out.println(listOfRequestForBooks.get(i).getBook().getTitle());
        }
    }

    public void sortRequestByCount() {
        RequestForBookCountComparator requestForBookCountComparator = new RequestForBookCountComparator();
        List<RequestForBook> requestForBooks = RequestForBookRepositoryImpl.getInstance().getAll();
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
        List<RequestForBook> requestForBooks = RequestForBookRepositoryImpl.getInstance().getAll();
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
        RequestForBook requestForBook = new RequestForBook(RequestForBookIdGenerator.getRequestForBookId(), book, RequestForBookStatus.ACTIVE, order);
        RequestForBookRepositoryImpl.getInstance().create(requestForBook);
        return requestForBook;
    }

    public void addRequestForBookToList(RequestForBook requestForBook){
        RequestForBookRepositoryImpl.getInstance().create(requestForBook);
    }

    public void updateRequestForBook(RequestForBook requestForBook){
        RequestForBookRepositoryImpl.getInstance().update(requestForBook);
    }

    public void deleteRequestForBook(RequestForBook requestForBook){
        RequestForBookRepositoryImpl.getInstance().delete(requestForBook);
    }

    public List<RequestForBook> getListOfRequestForBook(){
        List<RequestForBook> requestForBookList = RequestForBookRepositoryImpl.getInstance().getAll();
        return requestForBookList;
    }

    public RequestForBook getRequestForBookById(int id){
        RequestForBook requestForBook = RequestForBookRepositoryImpl.getInstance().read(id);
        return requestForBook;
    }
}
