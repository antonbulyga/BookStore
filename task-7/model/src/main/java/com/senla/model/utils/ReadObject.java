package main.java.com.senla.model.utils;

import annotation.MyInject;
import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.сontrollers.*;

import java.io.*;
import java.util.List;

public class ReadObject {
    @MyInject(key = "bookStoreData")
    private static String path;

    public static void read(){
        List<Book> books;
        List<Order> orders;
        List<RequestForBook> requestForBooks;
        List<Customer> customers;
        List<StockLevel> stockLevels;
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
                Store store = (Store) ois.readObject();
                books = store.getBooks();
                BookController.getInstance().setListOfBooksInStoreHouse(books);
                orders = store.getOrders();
                OrderController.getInstance().setListOfOrders(orders);
                requestForBooks = store.getRequestForBooks();
                RequestForBookController.getInstance().setListOfRequestForBook(requestForBooks);
                customers = store.getCustomers();
                CustomerController.getInstance().setListOfCustomers(customers);
                stockLevels = store.getStockLevels();
                StockLevelController.getInstance().setArrayOfStockLevels(stockLevels);
            }
            catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

}
