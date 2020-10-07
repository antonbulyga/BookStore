package main.java.com.senla.model.utils;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.*;
import main.java.com.senla.model.сontrollers.*;


import java.io.*;
import java.util.List;

@Component
public class ReadObject {
    @MyInject(key = "bookStoreData")
    private static String path;

    public static void read(){
        List<Book> books;
        List<Order> orders;
        List<RequestForBook> requestForBooks;
        List<Customer> customers;

            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
                Store store = (Store) ois.readObject();
                books = store.getBooks();
                orders = store.getOrders();
                requestForBooks = store.getRequestForBooks();
                customers = store.getCustomers();


            }
            catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

}
