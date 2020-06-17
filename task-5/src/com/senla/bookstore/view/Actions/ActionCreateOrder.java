package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Customer;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.сontrollers.BookController;
import com.senla.bookstore.model.сontrollers.CustomerController;
import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.model.сontrollers.StoreController;
import com.senla.bookstore.service.BookService;
import com.senla.bookstore.view.IAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ActionCreateOrder implements IAction {

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Book> listOfBooksForOrder = BookController.getInstance().getListOfBooksInStoreHouse();
        List<Customer> listOfCustomers = CustomerController.getInstance().getListOfCustomers();

        System.out.println("Choose index of the book for your order from the list of books");
        BookController.getInstance().showBooksInStock();
        int index = Integer.parseInt(reader.readLine());
        listOfBooksForOrder.add(BookController.getInstance().getListOfBooksInStoreHouse().get(index));
        Order order = OrderController.getInstance().createOrder(listOfBooksForOrder, listOfCustomers.get(0));
        OrderController.getInstance().addOrderToListOfOrders(order);
        StoreController.getInstance().addOrderToStore(order);
        StoreController.getInstance().executeOrder(order);
    }
}
