package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.entity.RequestForBook;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.model.сontrollers.RequestForBookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ActionImportRequestForBook implements IAction {
    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        List<Order> orders = OrderController.getInstance().getListOfOrders();
        List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
        try (BufferedReader reader = new BufferedReader(new FileReader(property.getProperty("requestForBookFile")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                while (true) {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    int bookId = Integer.parseInt(strings[1]);
                    String requestForBookStatus = strings[2];
                    int orderId = Integer.parseInt(strings[3]);
                    RequestForBook requestForBook = RequestForBookController.getInstance().createRequestForBook(books.get(bookId),orders.get(orderId));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }

}
