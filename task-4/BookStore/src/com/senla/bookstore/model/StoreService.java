package com.senla.bookstore.model;

import com.senla.bookstore.service.Store;

import java.time.LocalDate;

public class StoreService {
    private BookService bookService = new BookService();
    private OrderService orderService = new OrderService();
    private Store store = new Store();
    private RequestForBookService requestForBookService = new RequestForBookService();

    public void addOrderToStore(Order order, Store store) {
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

    public void executeOrder(Order order, Stock stock){
        LocalDate date = LocalDate.now();
        Book[] booksInOrder = order.getBooks();
        RequestForBook[] requestForBooksInOrder = order.getArrayOfRequestForBooks();
        StockLevel[] stockLevels = stock.getArrayOfStockLevels();
        int tmp;
        if (requestForBooksInOrder.length == 0) {
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
            for (int i = 0; i <stockLevels.length ; i++) {
                for (int j = 0; j < booksInOrder.length; j++) {
                    if(stockLevels[i].getBook().equals(booksInOrder[j])) {
                        tmp = stockLevels[i].getCount();
                        stockLevels[i].setCount(tmp - 1);
                    }
                }

            }
            stock.setArrayOfStockLevels(stockLevels);
        }
        else {
            System.out.println("The order was not completed because there are outstanding requests");
        }
    }

    public void arriveBookToStock(Book book, Stock stock) {
        int countOfBooksInStock;
        StockLevel[] stockLevels = stock.getArrayOfStockLevels();

        for (int i = 0; i < stockLevels.length; i++) {
            if(stockLevels[i].getBook() == book){
                stockLevels[i].getBook().setBookStatus(BookStatus.IN_STOCK);
                countOfBooksInStock = stockLevels[i].getCount();
                stockLevels[i].setCount(countOfBooksInStock++);
            }
        }
        stock.setArrayOfStockLevels(stockLevels);
    }

    public void completingRequestAfterArrivingNewBook( Book book, Stock stock) {
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
                            executeOrder(order, stock);
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

    public void showUnsoldBooksMoreThanSixMonth(Store store) {
        LocalDate date = LocalDate.now();
        Book[] books = store.getArrayOfBooksInStorehouse();
        Book[] arrayOfUnsoldBooksMoreThanSixMonth = new Book[0];
        System.out.println("Books unsold for more than 6 month : ");
        for (int i = 0; i < books.length; i++) {

            if(books[i].getBookStatus() == BookStatus.IN_STOCK) {
                LocalDate date2 = books[i].getArriveDate().plusMonths(6);
                int compareResult = date2.compareTo(date);
                if (compareResult < 0) {
                    arrayOfUnsoldBooksMoreThanSixMonth = bookService.addBook(store, books[i]);
                }
            }
        }
        for (int i = 0; i < arrayOfUnsoldBooksMoreThanSixMonth.length; i++) {
            System.out.println(arrayOfUnsoldBooksMoreThanSixMonth[i].getTitle());
        }
    }
}
