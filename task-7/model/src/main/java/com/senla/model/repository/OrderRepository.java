package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.OrderStatus;
import main.java.com.senla.model.utils.generators.OrderIdGenerator;
import main.java.com.senla.model.сontrollers.OrderController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static OrderRepository instance;
    private List<Order> listOfOrders = new ArrayList<>();

    private OrderRepository(){

    }

    public static OrderRepository getInstance(){
        if(instance == null){
            instance = new OrderRepository();
        }
        return instance;
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

    public void updateOrder(Order order) {
        List<Order> orders = OrderRepository.getInstance().getListOfOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order.getId()) {
                OrderController.getInstance().deleteOrder(orders.get(i));
                orders.add(order);
            }
        }
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

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }
}
