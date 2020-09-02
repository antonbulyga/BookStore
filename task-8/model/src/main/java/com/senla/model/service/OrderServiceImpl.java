package main.java.com.senla.model.service;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.repository.BookRepositoryImpl;
import main.java.com.senla.model.repository.OrderRepositoryImpl;
import main.java.com.senla.model.repository.RequestForBookRepositoryImpl;
import main.java.com.senla.model.repository.api.OrderRepository;
import main.java.com.senla.model.repository.api.RequestForBookRepository;
import main.java.com.senla.model.service.api.BookService;
import main.java.com.senla.model.service.api.CustomerService;
import main.java.com.senla.model.service.api.OrderService;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.utils.generators.OrderIdGenerator;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.сomparators.OrderDataOfDoneComparator;
import main.java.com.senla.model.сomparators.OrderPriceComparator;
import main.java.com.senla.model.сomparators.OrderStatusComparator;
import main.java.com.senla.model.enumeration.RequestForBookStatus;
import main.java.com.senla.model.enumeration.OrderStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class OrderServiceImpl implements OrderService {
    @MyAutoWired
    private OrderService orderService;
    @MyAutoWired
    private CustomerService customerService;
    @MyAutoWired
    private BookService bookService;
    @MyInject(key = "orderFile")
    private String path;

    public void importOrder(){
        List<Book> books = bookService.getListOfBooksInStoreHouse();
        List<Book> listOfBookInOrder = new ArrayList<>();
        List<Order> listOfOrders = orderService.getListOfOrders();
        List<Customer> customers = customerService.getListOfCustomers();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String dateOfOrder = strings[1];
                String dateOfDoneOrderString = strings[2];
                String listOfBooks = strings[3];
                int customerId = Integer.parseInt(strings[4]);
                double priceOfOrder = Double.parseDouble(strings[5]);
                String[] idBooksList = listOfBooks.split(" ");
                for (int i = 0; i <idBooksList.length ; i++) {
                    if(Integer.parseInt(idBooksList[i]) == books.get(i).getId()){
                        listOfBookInOrder.add(books.get(i));
                    }
                }
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
                LocalDate dateOfDoneOrder = LocalDate.parse(dateOfDoneOrderString, dateTimeFormatter);
                Order order = createOrder(listOfBookInOrder, customers.get(customerId), dateOfDoneOrder);
                for (int i = 0; i < listOfOrders.size(); i++) {
                    if(order.getId() == listOfOrders.get(i).getId()){
                        updateOrder(order);
                    }
                    else {
                        addOrderToListOfOrders(order);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("We have no file");
        }

    }

    public void exportOrder(){
        List<Order> orderList = getListOfOrders();
        ExportHelper.write(orderList, null, null, null, path);
    }

    public List<Order> getListOfOrders() {
        List<Order> orders = OrderRepositoryImpl.getInstance().getAll();
        return orders;
    }

    public void addOrderToListOfOrders(Order order){
        OrderRepositoryImpl.getInstance().create(order);
    }

    public Order createOrder(List<Book> books , Customer customer, LocalDate dateOfDoneOrder){
        int sumPriceOfOrder = 0;
        for (int i = 0; i < books.size(); i++) {
           sumPriceOfOrder += books.get(i).getPrice();
        }
        LocalDate date = LocalDate.now();
        Order order = new Order(OrderIdGenerator.getOrderId(), date, dateOfDoneOrder, books, OrderStatus.NEW, customer, sumPriceOfOrder);
        OrderRepositoryImpl.getInstance().create(order);
        return order;
    }

    public void addOrderToStore(Order order) {
        List<RequestForBook> listOfRequestsInOrder = new ArrayList<>();
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
                order.setListOfRequestForBooks(listOfRequestsInOrder);
                RequestForBookRepositoryImpl.getInstance().create(requestForBook);
            }
        }

    }

    public void executeOrder(Order order){
        LocalDate date = LocalDate.now();
        List<Book> booksInOrder = order.getBooks();
        List<RequestForBook> requestForBooksInOrder = order.getListOfRequestForBooks();
        List<Book> bookList = BookRepositoryImpl.getInstance().getAll();
        int tmp;
        if (requestForBooksInOrder.size() == 0) {
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
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
        sortOrdersByDateOfDone();
    }

    public void sortListOfDoneOrdersByPeriodOfTimeByPrice(List<Order> listOfDoneOrdersByPeriodOfTime){
        sortOrdersByPrice();
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
      OrderRepositoryImpl.getInstance().update(order);
    }

    public void showListOfOrders(){
        List<Order> orders = OrderRepositoryImpl.getInstance().getAll();
        System.out.println("List of orders: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + i);
        }
    }

    public void sortOrdersByDateOfDone() {
        OrderDataOfDoneComparator orderDataOfDoneComparator = new OrderDataOfDoneComparator();
        List<Order> orders = OrderRepositoryImpl.getInstance().getAll();
        Collections.sort(orders, orderDataOfDoneComparator);
        System.out.println("List of orders sorted by date of done: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getDateOfDoneOrder());
        }
    }

    public void sortOrdersByPrice() {
        OrderPriceComparator orderPriceComparator = new OrderPriceComparator();
        List<Order> orders = OrderRepositoryImpl.getInstance().getAll();
        Collections.sort(orders, orderPriceComparator);
        System.out.println("List of orders sorted by price: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " +orders.get(i).getPriceOfOrder());
        }
    }

    public void sortOrdersByStatus() {
        OrderStatusComparator orderStatusComparator = new OrderStatusComparator();
        List<Order> orders = OrderRepositoryImpl.getInstance().getAll();
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
        OrderRepositoryImpl.getInstance().delete(order);
    }

    public void changeOrderStatusToCancelled(Order order){
        order.setOrderStatus(OrderStatus.CANCELLED);
        List<RequestForBook> requestForBooks = order.getListOfRequestForBooks();
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
      Order order = OrderRepositoryImpl.getInstance().read(id);
        return order;
    }

}
