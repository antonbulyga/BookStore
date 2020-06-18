package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.entity.Book;
import com.senla.bookstore.model.entity.Order;
import com.senla.bookstore.model.сontrollers.BookController;
import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.model.сontrollers.RequestForBookController;
import com.senla.bookstore.view.api.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ActionCreateRequestForBook implements IAction {
    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Order> orders = OrderController.getInstance().getListOfOrders();
        List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
        BookController.getInstance().showBooksInStock();
        System.out.println("Fill in index of book");
        int indexOfBook = Integer.parseInt(reader.readLine());
        OrderController.getInstance().showListOfOrders();
        System.out.println("Fill in index of order");
        int indexOfOrder = Integer.parseInt(reader.readLine());
        RequestForBookController.getInstance().createRequestForBook(books.get(indexOfBook),orders.get(indexOfOrder));
    }
}
