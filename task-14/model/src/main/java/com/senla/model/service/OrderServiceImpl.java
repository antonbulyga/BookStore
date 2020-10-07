package com.senla.model.service;

import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.OrderStatus;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.repository.api.OrderRepository;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.CustomerService;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.ExportHelper;
import com.senla.model.utils.input.IntegerInput;
import com.senla.model.сomparators.OrderDataOfDoneComparator;
import com.senla.model.сomparators.OrderPriceComparator;
import com.senla.model.сomparators.OrderStatusComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RequestForBookService requestForBookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;
    @Value("${orderFile}")
    private String path;
    static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    public Order read(Integer orderId) {
        Order order = orderRepository.read(orderId);
        return order;
    }

    public void importOrder() {
        List<Book> books = bookService.getListOfBooksInStoreHouse();
        List<Book> listOfBookInOrder = new ArrayList<>();
        List<Order> listOfOrders = orderService.getListOfOrders();
        List<Customer> customers = customerService.getListOfCustomers();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
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
                for (int i = 0; i < idBooksList.length; i++) {
                    if (Integer.parseInt(idBooksList[i]) == books.get(i).getId()) {
                        listOfBookInOrder.add(books.get(i));
                    }
                }
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
                LocalDate dateOfDoneOrder = LocalDate.parse(dateOfDoneOrderString, dateTimeFormatter);
                Order order = createOrder(listOfBookInOrder, null, customers.get(customerId), dateOfDoneOrder);
                for (int i = 0; i < listOfOrders.size(); i++) {
                    if (order.getId() == listOfOrders.get(i).getId()) {
                        updateOrder(order);
                    } else {
                        addOrderToListOfOrders(order);
                    }
                }
            }

        } catch (IOException e) {
            logger.error("We have no file");
        }

    }

    public void exportOrder() {
        List<Order> orderList = getListOfOrders();
        ExportHelper.write(orderList, null, null, null, path);
    }

    public List<Order> getListOfOrders() {
        List<Order> orders = orderRepository.getAll();
        return orders;
    }

    public void addOrderToListOfOrders(Order order) {
        try {
            orderRepository.create(order);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public Order createOrder(List<Book> books, List<RequestForBook> requestForBooks, Customer customer, LocalDate dateOfDoneOrder) {
        Order order = null;
        int sumPriceOfOrder = 0;
        int orderId = 0;
        for (int i = 0; i < books.size(); i++) {
            sumPriceOfOrder += books.get(i).getPrice();
        }
        LocalDate date = LocalDate.now();
        order = new Order(date, dateOfDoneOrder, books, OrderStatus.NEW, customer, sumPriceOfOrder);
        order.setListOfRequestForBooks(requestForBooks);
        for (int i = 0; i < requestForBooks.size(); i++) {
            requestForBooks.get(i).setOrder(order);
        }
        for (int i = 0; i < books.size(); i++) {
            books.get(i).setOrder(order);
        }
        try {
            orderRepository.create(order);
            logger.debug("Order has been created");
            orderService.showListOfOrders();
            logger.info("Input id of the last order");
            orderId = IntegerInput.getInputInteger();
            for (int i = 0; i < requestForBooks.size(); i++) {
                requestForBooks.get(i).setOrder(orderRepository.read(orderId));
                requestForBookService.create(requestForBooks.get(i));
            }
            for (int i = 0; i < books.size(); i++) {
                bookService.bookUpdate(books.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void executeOrder(Order order) {
        LocalDate date = LocalDate.now();
        List<Book> listBookInOrder = order.getBooks();
        List<RequestForBook> requestForBooksInOrder = order.getListOfRequestForBooks();

        int tmp = 0;
        if (requestForBooksInOrder.size() == 0) {
            order.setOrderStatus(OrderStatus.DONE);
            order.setDateOfDoneOrder(date);
            orderRepository.update(order);
            logger.debug("Order has been updated");
        }
        for (int i = 0; i < requestForBooksInOrder.size(); i++) {
            if (requestForBooksInOrder.get(i).getRequestStatus() == RequestForBookStatus.COMPLETED) {
                tmp++;
            }
            if (tmp == requestForBooksInOrder.size()) {
                order.setOrderStatus(OrderStatus.DONE);
                order.setDateOfDoneOrder(date);
                for (int j = 0; j < listBookInOrder.size(); j++) {
                    bookService.deleteBook(listBookInOrder.get(j));
                    System.out.println("Book has been deleted");
                }
                for (int j = 0; j < requestForBooksInOrder.size(); j++) {
                    Book book = bookService.getBookByAuthorAndTitle(requestForBooksInOrder.get(i).getTitleOfBook(), requestForBooksInOrder.get(i).getAuthorOfBook());
                    bookService.deleteBook(book);
                }
                orderRepository.update(order);
            }
        }

    }

    public void listOfDoneOrdersByPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2) {
        List<Order> listOfDoneOrdersByPeriodOfTime = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) == 0) {
                listOfDoneOrdersByPeriodOfTime.add(orders.get(i));
            }
        }
        logger.debug("List of orders by period of time: ");
        for (int i = 0; i < listOfDoneOrdersByPeriodOfTime.size(); i++) {
            listOfDoneOrdersByPeriodOfTime.get(i);
        }
    }

    public void sortListOfDoneOrdersByPeriodOfTimeByDateOfDone(List<Order> listOfDoneOrdersByPeriodOfTime) {
        sortOrdersByDateOfDone();
    }

    public void sortListOfDoneOrdersByPeriodOfTimeByPrice(List<Order> listOfDoneOrdersByPeriodOfTime) {
        sortOrdersByPrice();
    }

    public void sumOfMoneyPerPeriodOfTime(List<Order> orders, LocalDate date1, LocalDate date2) {
        double sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDateOfDoneOrder().compareTo(date1) == -1 && orders.get(i).getDateOfDoneOrder().compareTo(date2) == 1 || orders.get(i).getDateOfDoneOrder().compareTo(date2) == 0 || orders.get(i).getDateOfDoneOrder().compareTo(date1) == 0) {
                sum += orders.get(i).getPriceOfOrder();
            }
        }
        logger.debug("Amount of orders by period of time " + " from " + date1 + " to " + date2 + " is " + sum);
    }

    public void updateOrder(Order order) {
        orderRepository.update(order);
    }

    public void showListOfOrders() {
        List<Order> orders = orderRepository.getAll();
        logger.debug("List of orders: ");
        for (int i = 0; i < orders.size(); i++) {
            logger.debug(orders.get(i).getId());
        }
    }

    public void sortOrdersByDateOfDone() {
        OrderDataOfDoneComparator orderDataOfDoneComparator = new OrderDataOfDoneComparator();
        List<Order> orders = orderRepository.getAll();
        Collections.sort(orders, orderDataOfDoneComparator);
        logger.debug("List of orders sorted by date of done: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getDateOfDoneOrder());
        }
    }

    public void sortOrdersByPrice() {
        OrderPriceComparator orderPriceComparator = new OrderPriceComparator();
        List<Order> orders = orderRepository.getAll();
        Collections.sort(orders, orderPriceComparator);
        logger.debug("List of orders sorted by price: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getPriceOfOrder());
        }
    }

    public void sortOrdersByStatus() {
        OrderStatusComparator orderStatusComparator = new OrderStatusComparator();
        List<Order> orders = orderRepository.getAll();
        Collections.sort(orders, orderStatusComparator);
        logger.debug("List of orders sorted by status: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getId() + " - " + orders.get(i).getOrderStatus());
        }
    }

    public void showDetailsOfOrder(Order order) {
        logger.debug("Details of the customer: " + "name: " + order.getCustomer().getName() + " age " + order.getCustomer().getAge());
        logger.debug("Details of the order : " + "price of order is " + order.getPriceOfOrder() + ", date of done order is: " + order.getDateOfDoneOrder());
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public void changeOrderStatusToCancelled(Order order) {
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
        logger.debug("Count of orders by period of time " + " from " + date1 + " to " + date2 + " is " + sum);
    }

    public Order getOrderById(int id) {
        Order order = orderRepository.read(id);
        return order;
    }

}
