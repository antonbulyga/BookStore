package com.senla.model.utils;

import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportHelper {
    public static void write(List<Order> orderList, List<Book> bookList, List<Customer> customerList, List<RequestForBook> requestForBookList, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            if(orderList != null) {
                for (int i = 0; i < orderList.size(); i++) {
                    writer.write(OrderStringForExportBuilder.orderStringBuilder(orderList.get(i)) + "\n");
                    writer.flush();
                }
            }
            if(bookList !=null){
                for (int i = 0; i < bookList.size(); i++) {
                    writer.write(BookStringForExportBuilder.bookStringBuilder(bookList.get(i)) + "\n");
                    writer.flush();
                }
            }
            if(customerList != null){
                for (int i = 0; i < customerList.size(); i++) {
                    writer.write(CustomerStringExportBuilder.customerStringBuilder(customerList.get(i)) + "\n");
                    writer.flush();
                }
            }
            if(requestForBookList != null){
                for (int i = 0; i < requestForBookList.size(); i++) {
                    writer.write(RequestForBookStringForExportBuilder.requestForBookStringBuilder(requestForBookList.get(i)) + "\n");
                    writer.flush();

                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
