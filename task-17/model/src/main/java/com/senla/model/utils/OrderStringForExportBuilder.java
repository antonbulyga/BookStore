package com.senla.model.utils;

import com.senla.model.entity.Book;
import com.senla.model.entity.Order;

import java.util.List;

public class OrderStringForExportBuilder {
    public static String orderStringBuilder(Order order){
        StringBuilder stringListOfBooksInOrder = new StringBuilder();
        StringBuilder stringOfOrder = new StringBuilder();
        List<Book> listBooksInOrder = order.getBooks();
        for (int j = 0; j < listBooksInOrder.size(); j++) {
            stringListOfBooksInOrder.append(listBooksInOrder.get(j).getId() + " ");
        }
        stringOfOrder.append(order.getId() + "," + order.getDateOfOrder() + "," + order.getDateOfDoneOrder() + "," + stringListOfBooksInOrder
        + "," + order.getPriceOfOrder()+ "," + order.getOrderStatus()+ "," + order.getCustomer().getId());
        String s = stringOfOrder.toString();
       return s;
    }
}
