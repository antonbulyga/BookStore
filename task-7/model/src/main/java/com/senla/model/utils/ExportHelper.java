package main.java.com.senla.model.utils;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportHelper {
    public static void write(List<Order> orderList, List<Book> bookList, List<Customer> customerList, List<RequestForBook> requestForBookList, String key) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(PropertyData.getProperty(key)))) {
            for (int i = 0; i < orderList.size(); i++) {
                writer.write(OrderStringForExportBuilder.orderStringBuilder(orderList.get(i)) + "\n");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
