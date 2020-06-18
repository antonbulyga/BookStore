package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.entity.Book;
import com.senla.bookstore.model.entity.Customer;
import com.senla.bookstore.model.entity.Order;
import com.senla.bookstore.model.сontrollers.BookController;
import com.senla.bookstore.model.сontrollers.CustomerController;
import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.model.сontrollers.StoreController;
import com.senla.bookstore.view.api.IAction;
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
