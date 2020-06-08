package com.senla.bookstore.model;

import com.senla.bookstore.service.Store;

import java.time.LocalDate;

public class StoreService {
    BookService bookService = new BookService();
    OrderService orderService = new OrderService();
    Store store = new Store();
    RequestForBookService requestForBookService = new RequestForBookService();

    public void bookSort() {
        bookService.sortBookByPrice(store.getArrayOfBooksInStorehouse());
        bookService.sortBookByAuthor(store.getArrayOfBooksInStorehouse());
        bookService.sortBookByDateArrive(store.getArrayOfBooksInStorehouse());
        bookService.sortBookByAvailabilityInStock(store.getArrayOfBooksInStorehouse());
        bookService.sortBookByPublicationDate(store.getArrayOfBooksInStorehouse());
    }

    public void orderSort() {
        orderService.sortOrdersByDateOfDone(store.getArrayOfOrders());
        orderService.sortOrdersByPrice(store.getArrayOfOrders());
        orderService.sortOrdersByStatus(store.getArrayOfOrders());
    }

    public void showInfoForUser(Order order1, Order order2) {
        sumOfMoneyPerPeriodOfTime(store.getArrayOfOrders());
        orderService.showDetailsOfOrder(order1);
        orderService.showDetailsOfOrder(order2);
        countOfDoneOrdersByPeriodOfTime(store.getArrayOfOrders());
        showUnsoldBooksMoreThanSixMonth(store.getArrayOfBooksInStorehouse());
    }

    public void requestSort(){
        requestForBookService.sortRequestByCount(store.getArrayOfRequestBooks());
    }

    public void checkingInStockStatus(Order order) {
        RequestForBook[] arrayOfRequestsInOrder = new RequestForBook[0];
        RequestForBook[] requestForBooks = store.getArrayOfRequestBooks();
        Book[] books = order.getBooks();
        Book[] arrayOfBookInStock = new Book[books.length];
        RequestForBook requestForBook;
        for (int i = 0; i < books.length; i++) {
            if(books[i].getBookStatus().equals(BookStatus.IN_STOCK)) {
                arrayOfBookInStock = bookService.addBook(arrayOfBookInStock, books[i]);
            }
            else {
                requestForBook = new RequestForBook(books[i], RequestForBookStatus.ACTIVE, order);
                arrayOfRequestsInOrder = requestForBookService.addBookRequest(arrayOfRequestsInOrder, requestForBook);
                books[i].setRequestForBooks(arrayOfRequestsInOrder);
                order.setArrayOfRequestForBooks(arrayOfRequestsInOrder);
                requestForBooks = requestForBookService.addBookRequest(requestForBooks, requestForBook);
                store.setArrayOfRequestBooks(requestForBooks);
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

    public void completingRequestAfterArrivingNewBook(RequestForBook[] requestForBooks, Book book, StockLevel[] stockLevels ) {
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
}
