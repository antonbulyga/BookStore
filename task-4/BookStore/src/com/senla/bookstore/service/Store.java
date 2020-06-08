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
    private RequestForBook[] arrayOfRequestBooks = new RequestForBook[0];
    private OrderService orderService = new OrderService();
    private RequestForBook requestForBook;
    private RequestForBookService requestForBookService = new RequestForBookService();
    private BookService bookService = new BookService();


    public void initializeArraysInStore(){
        Test test = new Test();
        Stock stock = new Stock();
        arrayOfBooksInStorehouse = test.initBook();
        test.initStockLevel(arrayOfBooksInStorehouse);
        arrayOfCustomers = test.initCustomer();
    }

    public void orderProcessing(Order order1, Order order2, StockLevel[] stockLevels) {
        checkingInStockStatus(order1);
        orderExecution(order1, stockLevels);
        arriveBookToStock(arrayOfBooksInStorehouse[3]);
        completingRequestAfterArrivingNewBook(arrayOfRequestBooks, arrayOfBooksInStorehouse[2], stockLevels);
        //deleteOrder(order1);
        requestForBookService.createRequestForBook(arrayOfBooksInStorehouse[2],order1);

        checkingInStockStatus(order2);
        orderExecution(order2, stockLevels);

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

    public void showInfoForUser(Order order1, Order order2) {
        sumOfMoneyPerPeriodOfTime(arrayOfOrders);
        orderService.showDetailsOfOrder(order1);
        orderService.showDetailsOfOrder(order2);
        countOfDoneOrdersByPeriodOfTime(arrayOfOrders);
        showUnsoldBooksMoreThanSixMonth(arrayOfBooksInStorehouse);
    }

    public void requestSort(){
         requestForBookService.sortRequestByCount(arrayOfRequestBooks);
    }

    public void checkingInStockStatus(Order order) {
        RequestForBook[] arrayOfRequestsInOrder = new RequestForBook[0];
        Book[] books = order.getBooks();
        Book[] arrayOfBookInStock = new Book[books.length];
        for (int i = 0; i < books.length; i++) {
            if(books[i].getBookStatus().equals(BookStatus.IN_STOCK)) {
                   arrayOfBookInStock = bookService.addBook(arrayOfBookInStock, books[i]);
               }
                 else {
                        requestForBook = new RequestForBook(books[i], RequestForBookStatus.ACTIVE, order);
                        arrayOfRequestsInOrder = requestForBookService.addBookRequest(arrayOfRequestsInOrder, requestForBook);
                        books[i].setRequestForBooks(arrayOfRequestsInOrder);
                        order.setArrayOfRequestForBooks(arrayOfRequestsInOrder);
                        arrayOfRequestBooks = requestForBookService.addBookRequest(arrayOfRequestBooks, requestForBook);
                    }
                }

    }

    public void orderExecution(Order order, StockLevel[] stockLevels){
        LocalDate date = LocalDate.now();
        Book[] books = order.getBooks();
        int tmp;
        int counter = 0;
        for (int i = 0; i < books.length ; i++) {
            if (books[i].getRequestForBooks().length == 0) {
                counter++;
            }
        }
        if (counter == books.length) {
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
            for (int i = 0; i <stockLevels.length ; i++) {
                tmp = stockLevels[i].getCount();
                stockLevels[i].setCount(tmp - 1);
            }
        }
    }

    public void arriveBookToStock(Book book) {
        int countOfBooksInStock;
        Stock stock = new Stock();
        StockLevel[] stockLevels = stock.getArrayOfStockLevels();

        for (int i = 0; i < stockLevels.length; i++) {
            if(stockLevels[i].getBook() == book){
                stockLevels[i].getBook().setBookStatus(BookStatus.IN_STOCK);
                countOfBooksInStock = stockLevels[i].getCount();
                stockLevels[i].setCount(countOfBooksInStock++);
            }
        }
    }

    public void completingRequestAfterArrivingNewBook(RequestForBook[] requestForBooks, Book book, StockLevel[] stockLevels) {
        RequestForBook[] requestForBooksLocal;
        Order order;
        for (int i = 0; i < requestForBooks.length; i++) {
            if(requestForBooks[i].getRequestStatus() == RequestForBookStatus.ACTIVE){
              order = requestForBooks[i].getOrder();
              requestForBooksLocal = order.getArrayOfRequestForBooks();
                for (int j = 0; j < requestForBooksLocal.length; j++) {
                    if(requestForBooksLocal[j].getBook() == book){
                        requestForBooks[i].setRequestStatus(RequestForBookStatus.COMPLETED);
                        requestForBooksLocal[j] = null;
                        orderExecution(order, stockLevels);
                    }
                }
            }

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

    public void showUnsoldBooksMoreThanSixMonth(Book[] books) {
        LocalDate date = LocalDate.now();
        Book[] arrayOfUnsoldBooksMoreThanSixMonth = new Book[0];
        for (int i = 0; i < books.length; i++) {
            if(books[i].getBookStatus() == BookStatus.IN_STOCK) {
                LocalDate date2 = books[i].getArriveDate().plusMonths(6);
                int compareResult = date2.compareTo(date);
                if (compareResult <0){
                    arrayOfUnsoldBooksMoreThanSixMonth = bookService.addBook(arrayOfUnsoldBooksMoreThanSixMonth, books[i]);
                }
            }
        }
        System.out.println("Books unsold for more than 6 month : ");
        for (int i = 0; i < arrayOfUnsoldBooksMoreThanSixMonth.length; i++) {

            System.out.println(arrayOfUnsoldBooksMoreThanSixMonth[i].getTitle());
        }
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

}
