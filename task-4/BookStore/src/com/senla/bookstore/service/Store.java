package com.senla.bookstore.service;

import com.senla.bookstore.controller.Test;
import com.senla.bookstore.model.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class Store {
    private Customer[] arrayOfCustomers = new Customer[0];
    private Order[] arrayOfOrders = new Order[0];
    private Book[] arrayOfBooksInStorehouse = new Book[0];
    private StockLevel[] arrayOfStockLevels = new StockLevel[0];
    private RequestForBook[] arrayOfRequestBooks = new RequestForBook[0];
    private OrderService orderService = new OrderService();
    private Test test = new Test();
    private RequestForBook requestForBook;
    private RequestForBookService requestForBookService = new RequestForBookService();
    private Order order1;
    private Order order2;
    private Book[] arrayOfBookForFirstOrder;
    private Book[] arrayOfBookForSecondOrder;
    private BookService bookService = new BookService();


    public void initializeArraysInStore(){
        arrayOfStockLevels = test.initStockLevel();
        arrayOfCustomers = test.initCustomer();
        arrayOfBooksInStorehouse = test.initBook(arrayOfStockLevels);
        arrayOfBookForFirstOrder = addBookForFirstOrder(arrayOfBooksInStorehouse);
        arrayOfBookForSecondOrder = addBookForSecondOrder(arrayOfBooksInStorehouse);

    }

    public void orderProcessing() {
        order1 = orderService.createOrder(arrayOfBookForFirstOrder, arrayOfCustomers[1]);
        arrayOfOrders = orderService.addOrder(arrayOfOrders, order1);
        checkingInStockStatus(order1, arrayOfRequestBooks);
        orderExecution(order1);
        arriveBookToStock(arrayOfBooksInStorehouse, arrayOfBooksInStorehouse[2]);
        completingRequestAfterArrivingNewBook(arrayOfRequestBooks, arrayOfBooksInStorehouse[2],arrayOfOrders);
        //deleteOrder(order1);

        order2 = orderService.createOrder(arrayOfBookForSecondOrder, arrayOfCustomers[2]);
        arrayOfOrders = orderService.addOrder(arrayOfOrders, order2);
        checkingInStockStatus(order2, arrayOfRequestBooks);
        orderExecution(order2);

    }

    public void bookSort() {
        bookService.sortBookByPrice(arrayOfBooksInStorehouse);
        bookService.sortBookByAuthor(arrayOfBooksInStorehouse);
        bookService.sortBookByDateArrive(arrayOfBooksInStorehouse);
        bookService.sortBookByAvailabilityInStock(arrayOfBooksInStorehouse);
        bookService.sortBookByPublicationDate(arrayOfBooksInStorehouse);
    }

    public void orderSort() {
        orderService.sortOrdersByDateOfDone(arrayOfOrders);
        orderService.sortOrdersByPrice(arrayOfOrders);
        orderService.sortOrdersByStatus(arrayOfOrders);
    }

    public void showInfoForUser() {
        sumOfMoneyPerPeriodOfTime(arrayOfOrders);
        orderService.showDetailsOfOrder(order1);
        countOfDoneOrdersByPeriodOfTime(arrayOfOrders);    }

    public void requestSort(){
         requestForBookService.sortRequestByCount(arrayOfRequestBooks);
    }

    public Book[] addBookForFirstOrder(Book[] arrayOfBookInStorehouse) {
        Book[] arrayOfBookForFirstOrder = new Book[4];
        arrayOfBookForFirstOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForFirstOrder[1] = arrayOfBookInStorehouse[4];
        arrayOfBookForFirstOrder[2] = arrayOfBookInStorehouse[2];
        arrayOfBookForFirstOrder[3] = arrayOfBookInStorehouse[3];

        return arrayOfBookForFirstOrder;
    }

    public Book[]  addBookForSecondOrder(Book[] arrayOfBookInStorehouse){
        Book[] arrayOfBookForSecondOrder = new Book[2];
        arrayOfBookForSecondOrder[0] = arrayOfBookInStorehouse[0];
        arrayOfBookForSecondOrder[1] = arrayOfBookInStorehouse[2];
        return arrayOfBookForSecondOrder;
    }
    public void checkingInStockStatus(Order order, RequestForBook[] requestForBooks) {
        RequestForBook[] arrayOfRequestsInOrder = new RequestForBook[0];
        Book[] books = order.getBooks();
        Book[] arrayOfBookInStock = new Book[books.length];
        int count;
        for (int i = 0; i < books.length; i++) {
            for (int j = 0; j < requestForBooks.length; j++) {
                if(books[i].getBookStatus() == BookStatus.OUT_OF_STOCK){
                    if(books[i].getId() == requestForBooks[j].getBookId()){
                        count = requestForBooks[j].getCount();
                        requestForBooks[j].setCount(count++);
                    }
                    else {
                        requestForBook = new RequestForBook(books[i].getId(), RequestForBookStatus.ACTIVE, 1, order.getId());
                        arrayOfRequestsInOrder = requestForBookService.addBookRequest(arrayOfRequestsInOrder, requestForBook);
                        books[i].setRequestForBook(requestForBook);
                        order.setArrayOfRequestForBooks(arrayOfRequestsInOrder);
                        arrayOfRequestBooks = requestForBookService.addBookRequest(arrayOfRequestBooks, requestForBook);
                    }
                }
                else {
                    arrayOfBookInStock[i] = books[i];
                    order.setBooks(arrayOfBookInStock);
                }
            }
        }
    }

    public void orderExecution(Order order){
        LocalDate date = LocalDate.now();
        Book[] books = order.getBooks();
        int tmp;
        int counter = 0;
        for (int i = 0; i < books.length ; i++) {
            if (books[i].getRequestForBook() == null) {
                counter++;
            }
        }
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
        if (counter == books.length) {
            for (int i = 0; i <books.length ; i++) {
                tmp = books[i].getStockLevel().getCount();
                books[i].getStockLevel().setCount(tmp - 1);
            }
        }
    }

    public void arriveBookToStock(Book[] books, Book book) {
        int countOfBooksInStock;
        for (int i = 0; i < books.length; i++) {
            if(books[i] == book){
                books[i].setBookStatus(BookStatus.IN_STOCK);
                countOfBooksInStock = books[i].getStockLevel().getCount();
                books[i].getStockLevel().setId(books[i].getId());
                books[i].getStockLevel().setCount(countOfBooksInStock++);
            }
        }
    }

    public void completingRequestAfterArrivingNewBook(RequestForBook[] requestForBooks, Book book, Order[] orders) {
        RequestForBook[] requestForBooksLocal;
        for (int i = 0; i < requestForBooks.length; i++) {
            if(requestForBooks[i].getRequestStatus() == RequestForBookStatus.ACTIVE){
              int orderId = requestForBooks[i].getOrderId();
              requestForBooksLocal = orders[orderId].getArrayOfRequestForBooks();
                for (int j = 0; j < requestForBooksLocal.length; j++) {
                    if(requestForBooksLocal[j].getBookId() == book.getId() ){
                        requestForBooks[i].setRequestStatus(RequestForBookStatus.COMPLETED);
                        requestForBooksLocal[j] = null;
                    }
                }
            }
            orderExecution(orders[i]);
        }
    }

    public void deleteOrder(Order order){
        RequestForBook[] requestForBooksLocal = order.getArrayOfRequestForBooks();
        for (int i = 0; i < arrayOfRequestBooks.length; i++) {
            for (int j = 0; j < requestForBooksLocal.length; j++) {
                if(arrayOfRequestBooks[i] == requestForBooksLocal[j]){
                    arrayOfRequestBooks[i] = null;
                }
            }
        }
        for (int i = 0; i < arrayOfOrders.length; i++) {
            if(order.getId() == arrayOfOrders[i].getId()){
                arrayOfOrders[i] = null;
            }
        }
    }

    public void changeOrderStatusToCancelled(Order order){
        order.setOrderStatus(OrderStatus.CANCELLED);
        RequestForBook[] requestForBooks = order.getArrayOfRequestForBooks();
        for (int i = 0; i < requestForBooks.length; i++) {
            requestForBooks[i].setRequestStatus(RequestForBookStatus.CANCELLED);
        }
    }
    public void sumOfMoneyPerPeriodOfTime(Order[] orders) {
        LocalDate date1 = LocalDate.of(2020,06,01);
        LocalDate date2 = LocalDate.of(2020,06,07);
        double sum = 0;
        for (int i = 0; i < orders.length; i++) {
            if(orders[i].getDateOfDoneOrder().compareTo(date1) == -1 && orders[i].getDateOfDoneOrder().compareTo(date2) == 1 || orders[i].getDateOfDoneOrder().compareTo(date2) == 0 || orders[i].getDateOfDoneOrder().compareTo(date1) ==0 ){
                sum += orders[i].getPriceOfOrder();
            }
        }
        System.out.println("Amount of orders by period of time " + " from " + date1 + " to "+ date2 + " is "+ sum);
    }

    public void countOfDoneOrdersByPeriodOfTime(Order[] orders) {
        LocalDate date1 = LocalDate.of(2020, 06, 01);
        LocalDate date2 = LocalDate.of(2020, 06, 07);
        int sum = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getDateOfDoneOrder().compareTo(date1) == -1 && orders[i].getDateOfDoneOrder().compareTo(date2) == 1 || orders[i].getDateOfDoneOrder().compareTo(date2) == 0 || orders[i].getDateOfDoneOrder().compareTo(date1) == 0) {
                sum++;
            }
        }
        System.out.println("Count of orders by period of time " + " from " + date1 + " to " + date2 + " is " + sum);
    }

       public Customer[] getArrayOfCustomers() {
            return arrayOfCustomers;
        }

    public void setArrayOfCustomers(Customer[] arrayOfCustomers) {
        this.arrayOfCustomers = arrayOfCustomers;
    }

    public Order[] getArrayOfOrders() {
        return arrayOfOrders;
    }

    public void setArrayOfOrders(Order[] arrayOfOrders) {
        this.arrayOfOrders = arrayOfOrders;
    }

    public Book[] getArrayOfBooksInStorehouse() {
        return arrayOfBooksInStorehouse;
    }

    public void setArrayOfBooksInStorehouse(Book[] arrayOfBooksInStorehouse) {
        this.arrayOfBooksInStorehouse = arrayOfBooksInStorehouse;
    }
    public StockLevel[] getArrayOfStockLevels() {
        return arrayOfStockLevels;
    }

    public void setArrayOfStockLevels(StockLevel[] arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }
}
