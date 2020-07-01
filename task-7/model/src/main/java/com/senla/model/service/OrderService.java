package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.repository.OrderRepository;
import main.java.com.senla.model.repository.RequestForBookRepository;
import main.java.com.senla.model.repository.StockRepository;
import main.java.com.senla.model.utils.generators.OrderIdGenerator;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.сomparators.OrderDataOfDoneComparator;
import main.java.com.senla.model.сomparators.OrderPriceComparator;
import main.java.com.senla.model.сomparators.OrderStatusComparator;
import main.java.com.senla.model.сomparators.RequestForBookStatus;
import main.java.com.senla.model.enumeration.OrderStatus;
import main.java.com.senla.model.сontrollers.OrderController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderService {
    private static OrderService instance;

    private OrderService() {

    }

    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    public List<Order> getListOfOrders() {
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        return orders;
    }

    public void setListOfOrders(List<Order> orders){
        OrderRepository.getInstance().setListOfOrders(orders);
    }

    public void addOrderToListOfOrders(Order order){
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        orders.add(order);
        OrderRepository.getInstance().setListOfOrders(orders);
    }

    public Order createOrder(List<Book> books , Customer customer, LocalDate dateOfDoneOrder){
        LocalDate date = LocalDate.now();
        int priceOfOrder = 0;
        List<Order> listOfOrders = getListOfOrders();
        Order order = new Order(OrderIdGenerator.getOrderId(), date , dateOfDoneOrder, books, OrderStatus.NEW, customer, 0);
        for (int i = 0; i < books.size(); i++) {
           priceOfOrder += books.get(i).getPrice();
        }
        order.setPriceOfOrder(priceOfOrder);
        return order;
    }

    public void addOrderToStore(Order order) {
        List<RequestForBook> listOfRequestsInOrder = new ArrayList<>();
        List<RequestForBook> requestForBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        List<Book> books = order.getBooks();
        List<Book> listOfBookInStock = new ArrayList<>();
        RequestForBook requestForBook;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getBookStatus().equals(BookStatus.IN_STOCK)) {
                listOfBookInStock.add(books.get(i));
            }
            else {
                requestForBook = new RequestForBook(RequestForBookIdGenerator.getRequestForBookId(), books.get(i), RequestForBookStatus.ACTIVE, order);
                listOfRequestsInOrder.add(requestForBook);
                books.get(i).setRequestForBooks(listOfRequestsInOrder);
                order.setArrayOfRequestForBooks(listOfRequestsInOrder);
                requestForBooks.add(requestForBook);
                RequestForBookRepository.getInstance().setListOfRequestForBooks(requestForBooks);
            }
        }

    }

    public void executeOrder(Order order){
        LocalDate date = LocalDate.now();
        List<Book> booksInOrder = order.getBooks();
        List<RequestForBook> requestForBooksInOrder = order.getArrayOfRequestForBooks();
        List<StockLevel> stockLevels = StockRepository.getInstance().getListOfStockLevels();
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
            StockRepository.getInstance().setListOfStockLevels(stockLevels);
        }
        else {
            System.out.println("The order was not completed because there are outstanding requests");
        }
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

    public void sumOfMoneyPerPeriodOfTime(List<Order> orders , LocalDate date1, LocalDate date2) {
        double sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) ==0 ){
                sum += orders.get(i).getPriceOfOrder();
            }
        }
        System.out.println("Amount of orders by period of time " + " from " + date1 + " to "+ date2 + " is "+ sum);
    }

    public void updateOrder(Order order) {
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order.getId()) {
                OrderController.getInstance().deleteOrder(orders.get(i));
                orders.add(order);
            }
        }

    }

    public void showListOfOrders(){
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        System.out.println("List of orders: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + i);
        }
    }

    public void sortOrdersByDateOfDone() {
        OrderDataOfDoneComparator orderDataOfDoneComparator = new OrderDataOfDoneComparator();
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        Collections.sort(orders, orderDataOfDoneComparator);
        System.out.println("List of orders sorted by date of done: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getDateOfDoneOrder());
        }
    }

    public void sortOrdersByPrice() {
        OrderPriceComparator orderPriceComparator = new OrderPriceComparator();
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        Collections.sort(orders, orderPriceComparator);
        System.out.println("List of orders sorted by price: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " +orders.get(i).getPriceOfOrder());
        }
    }

    public void sortOrdersByStatus() {
        OrderStatusComparator orderStatusComparator = new OrderStatusComparator();
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        Collections.sort(orders, orderStatusComparator);
        System.out.println("List of orders sorted by status: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " +orders.get(i).getOrderStatus());
        }
    }

    public void showDetailsOfOrder(Order order){
        System.out.println("Details of the customer: " + "name: " + order.getCustomer().getName() + " age " + order.getCustomer().getAge());
        System.out.println("Details of the order : " + "price of order is " +  order.getPriceOfOrder() + ", date of done order is: " +order.getDateOfDoneOrder());
    }

    public void deleteOrder(Order order){
        List<RequestForBook> arrayOfRequestBooks = RequestForBookRepository.getInstance().getListOfRequestForBooks();
        List<RequestForBook> requestForBooksLocal = order.getArrayOfRequestForBooks();
        List<Order> arrayOfOrders = OrderRepository.getInstance().getListOfOrders();
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
        RequestForBookRepository.getInstance().setListOfRequestForBooks(arrayOfRequestBooks);
        OrderRepository.getInstance().setListOfOrders(arrayOfOrders);
    }

    public void changeOrderStatusToCancelled(Order order){
        order.setOrderStatus(OrderStatus.CANCELLED);
        List<RequestForBook> requestForBooks = order.getArrayOfRequestForBooks();
        for (int i = 0; i < requestForBooks.size(); i++) {
            requestForBooks.get(i).setRequestStatus(RequestForBookStatus.CANCELLED);
        }
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


    public void orderSort() {
        sortOrdersByDateOfDone();
        sortOrdersByPrice();
        sortOrdersByStatus();
    }

    public Order getOrderById(int id){
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        Order order = null;
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getId() == id){
                order = orders.get(i);
            }
        }
        return order;
    }

}
