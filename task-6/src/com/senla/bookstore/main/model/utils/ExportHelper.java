package com.senla.bookstore.main.model.utils;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.entity.RequestForBook;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportHelper {
    public static void write(List<Order> orderList, List<Book> bookList, List<Customer> customerList, List<RequestForBook> requestForBookList, String key) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(PropertyPath.choose(key)))) {
            for (int i = 0; i < orderList.size(); i++) {
                writer.write(OrderStringForExportBuilder.orderStringBuilder(orderList.get(i)) + "\n");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
