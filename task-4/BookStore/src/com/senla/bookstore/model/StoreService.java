package com.senla.bookstore.model;

import com.senla.bookstore.service.Store;

import java.time.LocalDate;

public class StoreService {
    private BookService bookService = new BookService();
    private OrderService orderService = new OrderService();
    private Store store = new Store();
    private RequestForBookService requestForBookService = new RequestForBookService();

    public void requestSort(){
        requestForBookService.sortRequestByCount(store.getArrayOfRequestBooks());
    }

    public void checkingInStockStatus(Order order, Store store) {
        RequestForBook[] arrayOfRequestsInOrder;
        RequestForBook[] requestForBooks = store.getArrayOfRequestBooks();
        Book[] books = order.getBooks();
        Book[] arrayOfBookInStock = new Book[books.length];
        RequestForBook requestForBook;
        for (int i = 0; i < books.length; i++) {
            if(books[i].getBookStatus().equals(BookStatus.IN_STOCK)) {
                arrayOfBookInStock = bookService.addBook(store, books[i]);
            }
            else {
                requestForBook = new RequestForBook(books[i], RequestForBookStatus.ACTIVE, order);
                arrayOfRequestsInOrder = requestForBookService.addBookRequest(store, requestForBook);
                books[i].setRequestForBooks(arrayOfRequestsInOrder);
                order.setArrayOfRequestForBooks(arrayOfRequestsInOrder);
                requestForBooks = requestForBookService.addBookRequest(store, requestForBook);
                store.setArrayOfRequestBooks(requestForBooks);
            }
        }

    }

    public void orderExecution(Order order, StockLevel[] stockLevels){
        LocalDate date = LocalDate.now();
        Book[] booksInOrder = order.getBooks();
        RequestForBook[] requestForBooks = order.getArrayOfRequestForBooks();
        int tmp;
        if (requestForBooks.length == 0) {
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
            for (int i = 0; i <stockLevels.length ; i++) {
                if(stockLevels[i].equals(booksInOrder[i])) {
                    tmp = stockLevels[i].getCount();
                    stockLevels[i].setCount(tmp - 1);
                }
            }
        }
    }

    public void arriveBookToStock(Book book, StockLevel[] stockLevels) {
        int countOfBooksInStock;
        Stock stock = new Stock();

        for (int i = 0; i < stockLevels.length; i++) {
            if(stockLevels[i].getBook() == book){
                stockLevels[i].getBook().setBookStatus(BookStatus.IN_STOCK);
                countOfBooksInStock = stockLevels[i].getCount();
                stockLevels[i].setCount(countOfBooksInStock++);
            }
        }
    }

    public void completingRequestAfterArrivingNewBook( Book book, StockLevel[] stockLevels) {
        RequestForBook[] requestForBooks = store.getArrayOfRequestBooks();
        RequestForBook[] requestForBooksLocal;
        Order order;
        Order[] arraysOfOrders = store.getArrayOfOrders();
        for (int i = 0; i < requestForBooks.length; i++) {
            if(requestForBooks[i].getRequestStatus() == RequestForBookStatus.ACTIVE){
                order = requestForBooks[i].getOrder();
                requestForBooksLocal = order.getArrayOfRequestForBooks();
                for (int j = 0; j < requestForBooksLocal.length; j++) {
                    if(requestForBooksLocal[j].getBook() == book){
                        requestForBooks[i].setRequestStatus(RequestForBookStatus.COMPLETED);
                        requestForBooksLocal[j] = null;
                        store.setArrayOfOrders(arraysOfOrders);
                        if(requestForBooksLocal.length == 0) {
                            orderExecution(order, stockLevels);
                        }
                    }
                }
            }

        }
    }

    public void deleteOrder(Order order){
        RequestForBook[] arrayOfRequestBooks = store.getArrayOfRequestBooks();
        RequestForBook[] requestForBooksLocal = order.getArrayOfRequestForBooks();
        Order[] arrayOfOrders = store.getArrayOfOrders();
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
        store.setArrayOfRequestBooks(arrayOfRequestBooks);
        store.setArrayOfOrders(arrayOfOrders);
    }

    public void changeOrderStatusToCancelled(Order order){
        order.setOrderStatus(OrderStatus.CANCELLED);
        RequestForBook[] requestForBooks = order.getArrayOfRequestForBooks();
        for (int i = 0; i < requestForBooks.length; i++) {
            requestForBooks[i].setRequestStatus(RequestForBookStatus.CANCELLED);
        }
    }
    public void sumOfMoneyPerPeriodOfTime(Order[] orders , LocalDate date1, LocalDate date2) {
        double sum = 0;
        for (int i = 0; i < orders.length; i++) {
            if(orders[i].getDateOfDoneOrder().compareTo(date1) == -1 && orders[i].getDateOfDoneOrder().compareTo(date2) == 1 || orders[i].getDateOfDoneOrder().compareTo(date2) == 0 || orders[i].getDateOfDoneOrder().compareTo(date1) ==0 ){
                sum += orders[i].getPriceOfOrder();
            }
        }
        System.out.println("Amount of orders by period of time " + " from " + date1 + " to "+ date2 + " is "+ sum);
    }

    public void countOfDoneOrdersByPeriodOfTime(Order[] orders, LocalDate date1, LocalDate date2) {

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
                    arrayOfUnsoldBooksMoreThanSixMonth = bookService.addBook(store, books[i]);
                }
                System.out.println("Books unsold for more than 6 month : ");
                System.out.println(arrayOfUnsoldBooksMoreThanSixMonth[i].getTitle());
            }
        }
    }
}
