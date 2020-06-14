package com.senla.bookstore.service;

import com.senla.bookstore.model.*;
import com.senla.bookstore.model.Comparators.RequestForBookStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private Store store = new Store();

    public void addOrderToStore(Order order, Store store) {
        List<RequestForBook> arrayOfRequestsInOrder = new ArrayList<>();
        List<RequestForBook> requestForBooks = store.getListOfRequestBooks();
        List<Book> books = order.getBooks();
        List<Book> arrayOfBookInStock = new ArrayList<>();
        RequestForBook requestForBook;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getBookStatus().equals(BookStatus.IN_STOCK)) {
                arrayOfBookInStock.add(books.get(i));
            }
            else {
                requestForBook = new RequestForBook(books.get(i), RequestForBookStatus.ACTIVE, order);
                arrayOfRequestsInOrder.add(requestForBook);
                books.get(i).setRequestForBooks(arrayOfRequestsInOrder);
                order.setArrayOfRequestForBooks(arrayOfRequestsInOrder);
                requestForBooks.add(requestForBook);
                store.setListOfRequestBooks(requestForBooks);
            }
        }

    }

    public void executeOrder(Order order, Stock stock){
        LocalDate date = LocalDate.now();
        List<Book> booksInOrder = order.getBooks();
        List<RequestForBook> requestForBooksInOrder = order.getArrayOfRequestForBooks();
        List<StockLevel> stockLevels = stock.getArrayOfStockLevels();
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
            stock.setArrayOfStockLevels(stockLevels);
        }
        else {
            System.out.println("The order was not completed because there are outstanding requests");
        }
    }

    public void arriveBookToStock(Book book, Stock stock) {
        int countOfBooksInStock;
        List<StockLevel> stockLevels = stock.getArrayOfStockLevels();

        for (int i = 0; i < stockLevels.size(); i++) {
            if(stockLevels.get(i).getBook() == book){
                stockLevels.get(i).getBook().setBookStatus(BookStatus.IN_STOCK);
                countOfBooksInStock = stockLevels.get(i).getCount();
                stockLevels.get(i).setCount(countOfBooksInStock++);
            }
        }
        stock.setArrayOfStockLevels(stockLevels);
    }

    public void completingRequestAfterArrivingNewBook( Book book, Stock stock) {
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
                            executeOrder(order, stock);
                        }
                    }
                }
            }

        }
    }

    public void deleteOrder(Order order){
        List<RequestForBook> arrayOfRequestBooks = store.getListOfRequestBooks();
        List<RequestForBook> requestForBooksLocal = order.getArrayOfRequestForBooks();
        List<Order> arrayOfOrders = store.getListOfOrders();
        for (int i = 0; i < arrayOfRequestBooks.size(); i++) {
            for (int j = 0; j < requestForBooksLocal.size(); j++) {
                if(arrayOfRequestBooks.get(i) == requestForBooksLocal.get(j)){
                    arrayOfRequestBooks.remove(arrayOfRequestBooks.get(i));
                }
            }
        }
        for (int i = 0; i < arrayOfOrders.size(); i++) {
            if(order.getId() == arrayOfOrders.get(i).getId()){
                arrayOfOrders.remove(arrayOfOrders.get(i));
            }
        }
        store.setListOfRequestBooks(arrayOfRequestBooks);
        store.setListOfOrders(arrayOfOrders);
    }

    public void changeOrderStatusToCancelled(Order order){
        order.setOrderStatus(OrderStatus.CANCELLED);
        List<RequestForBook> requestForBooks = order.getArrayOfRequestForBooks();
        for (int i = 0; i < requestForBooks.size(); i++) {
            requestForBooks.get(i).setRequestStatus(RequestForBookStatus.CANCELLED);
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

    public void countOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2) {
        int sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) == 0) {
                sum++;
            }
        }
        System.out.println("Count of orders by period of time " + " from " + date1 + " to " + date2 + " is " + sum);
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
        OrderService orderService = new OrderService();
        orderService.sortOrdersByDateOfDone(listOfDoneOrdersByPeriodOfTime);
    }

    public void sortListOfDoneOrdersByPeriodOfTimeByPrice(List<Order> listOfDoneOrdersByPeriodOfTime){
        OrderService orderService = new OrderService();
        orderService.sortOrdersByPrice(listOfDoneOrdersByPeriodOfTime);
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
}
