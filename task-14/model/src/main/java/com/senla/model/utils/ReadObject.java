package com.senla.model.utils;

import com.senla.model.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

@Component
public class ReadObject {
    @Value("${bookStoreData}")
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
