package com.senla.model.utils;

import com.senla.model.entity.*;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.CustomerService;
import com.senla.model.service.api.OrderService;
import com.senla.model.service.api.RequestForBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReadObject {
    @Value("${bookStoreData}")
    private  String path;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RequestForBookService requestForBookService;

    public void read(){
        List<Book> books;
        List<Order> orders;
        List<RequestForBook> requestForBooks;
        List<Customer> customers;


            try{
                WriteObject writeObject = BeanGetter.getInstance().getWriteObjectBean();
                writeObject.write();
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
                Store.setInstance((Store) ois.readObject());
                books = Store.getInstance().getBooks();
                orders = Store.getInstance().getOrders();
                requestForBooks = Store.getInstance().getRequestForBooks();
                customers = Store.getInstance().getCustomers();
                for (int i = 0; i < books.size(); i++) {
                    bookService.createBook(books.get(i));
                }
                for (int i = 0; i < orders.size(); i++) {
                    orderService.addOrderToListOfOrders(orders.get(i));
                }
                for (int i = 0; i < requestForBooks.size(); i++) {
                    requestForBookService.addRequestForBookToList(requestForBooks.get(i));
                }
                for (int i = 0; i < customers.size(); i++) {
                    customerService.addCustomerToListOfCustomers(customers.get(i));
                }

            }
            catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
