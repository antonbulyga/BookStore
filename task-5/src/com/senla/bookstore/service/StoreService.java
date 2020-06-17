package com.senla.bookstore.service;

import com.senla.bookstore.model.*;
import com.senla.bookstore.model.Comparators.RequestForBookStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private static StoreService instance;
    private Store store = new Store();

    private StoreService() {

    }

    public static StoreService getInstance(){
        if(instance == null){
            instance = new StoreService();
        }
        return instance;
    }

    public void addOrderToStore(Order order) {
        List<RequestForBook> listOfRequestsInOrder = new ArrayList<>();
        List<RequestForBook> requestForBooks = store.getListOfRequestBooks();
        List<Book> books = order.getBooks();
        List<Book> listOfBookInStock = new ArrayList<>();
        RequestForBook requestForBook;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getBookStatus().equals(BookStatus.IN_STOCK)) {
                listOfBookInStock.add(books.get(i));
            }
            else {
                requestForBook = new RequestForBook(books.get(i), RequestForBookStatus.ACTIVE, order);
                listOfRequestsInOrder.add(requestForBook);
                books.get(i).setRequestForBooks(listOfRequestsInOrder);
                order.setArrayOfRequestForBooks(listOfRequestsInOrder);
                requestForBooks.add(requestForBook);
                store.setListOfRequestBooks(requestForBooks);
            }
        }

    }

    public void executeOrder(Order order){
        LocalDate date = LocalDate.now();
        List<Book> booksInOrder = order.getBooks();
        List<RequestForBook> requestForBooksInOrder = order.getArrayOfRequestForBooks();
        List<StockLevel> stockLevels = store.getStock().getArrayOfStockLevels();
        int tmp;
        if (requestForBooksInOrder.size() == 0) {
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
            for (int i = 0; i <stockLevels.size() ; i++) {
                for (int j = 0; j < booksInOrder.size(); j++) {
                    if(stockLevels.get(i).getBook().equals(booksInOrder.get(j))) {
                        tmp = stockLevels.get(i).getCount();
                        stockLevels.get(i).setCount(tmp - 1);
                    }
                }

            }
            store.getStock().setArrayOfStockLevels(stockLevels);
        }
        else {
            System.out.println("The order was not completed because there are outstanding requests");
        }
    }

    public void arriveBookToStock(Book book) {
        int countOfBooksInStock;
        List<StockLevel> stockLevels = store.getStock().getArrayOfStockLevels();

        for (int i = 0; i < stockLevels.size(); i++) {
            if(stockLevels.get(i).getBook() == book){
                stockLevels.get(i).getBook().setBookStatus(BookStatus.IN_STOCK);
                countOfBooksInStock = stockLevels.get(i).getCount();
                stockLevels.get(i).setCount(countOfBooksInStock++);
            }
        }
        store.getStock().setArrayOfStockLevels(stockLevels);
    }

    public void completingRequestAfterArrivingNewBook(Book book) {
        List<RequestForBook> requestForBooks = store.getListOfRequestBooks();
        List<RequestForBook> requestForBooksLocal;
        Order order;
        List<Order> arraysOfOrders = store.getListOfOrders();
        for (int i = 0; i < requestForBooks.size(); i++) {
            if(requestForBooks.get(i).getRequestStatus() == RequestForBookStatus.ACTIVE){
                order = requestForBooks.get(i).getOrder();
                requestForBooksLocal = order.getArrayOfRequestForBooks();
                for (int j = 0; j < requestForBooksLocal.size(); j++) {
                    if(requestForBooksLocal.get(j).getBook() == book){
                        requestForBooks.get(i).setRequestStatus(RequestForBookStatus.COMPLETED);
                        requestForBooksLocal.remove(requestForBooksLocal.get(j));
                        store.setListOfOrders(arraysOfOrders);
                        if(requestForBooksLocal.size() == 0) {
                            executeOrder(order);
                        }
                    }
                }
            }

        }
    }


    public void sumOfMoneyPerPeriodOfTime(List<Order> orders , LocalDate date1, LocalDate date2) {
        double sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) ==0 ){
                sum += orders.get(i).getPriceOfOrder();
            }
        }
        System.out.println("Amount of orders by period of time " + " from " + date1 + " to "+ date2 + " is "+ sum);
    }


    public void listOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2){
        List<Order> listOfDoneOrdersByPeriodOfTime = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) == 0) {
                listOfDoneOrdersByPeriodOfTime.add(orders.get(i));
            }
        }
        System.out.println("List of orders by period of time: ");
        for (int i = 0; i < listOfDoneOrdersByPeriodOfTime.size(); i++) {
            listOfDoneOrdersByPeriodOfTime.get(i);
        }
    }

    public void sortListOfDoneOrdersByPeriodOfTimeByDateOfDone(List<Order> listOfDoneOrdersByPeriodOfTime){
        OrderService orderService = OrderService.getInstance();
        orderService.sortOrdersByDateOfDone();
    }

    public void sortListOfDoneOrdersByPeriodOfTimeByPrice(List<Order> listOfDoneOrdersByPeriodOfTime){
        OrderService orderService = OrderService.getInstance();
        orderService.sortOrdersByPrice();
    }




    public void showUnsoldBooksMoreThanSixMonth(Store store) {
        LocalDate date = LocalDate.now();
        List<Book> books = store.getListOfBooksInStorehouse();
        List<Book> arrayOfUnsoldBooksMoreThanSixMonth = new ArrayList<>();
        System.out.println("Books unsold for more than 6 month : ");
        for (int i = 0; i < books.size(); i++) {

            if(books.get(i).getBookStatus() == BookStatus.IN_STOCK) {
                LocalDate date2 = books.get(i).getArriveDate().plusMonths(6);
                int compareResult = date2.compareTo(date);
                if (compareResult < 0) {
                    arrayOfUnsoldBooksMoreThanSixMonth.add(books.get(i));
                }
            }
        }
        for (int i = 0; i < arrayOfUnsoldBooksMoreThanSixMonth.size(); i++) {
            System.out.println(arrayOfUnsoldBooksMoreThanSixMonth.get(i).getTitle());
        }
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
