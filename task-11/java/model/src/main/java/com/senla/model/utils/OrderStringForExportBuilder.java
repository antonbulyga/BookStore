package main.java.com.senla.model.utils;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;

import java.util.List;

public class OrderStringForExportBuilder {
    public static String orderStringBuilder(Order order){
        String string = null;
        StringBuilder stringListOfBooksInOrder = new StringBuilder();
        StringBuilder stringOfOrder = new StringBuilder(string);
        List<Book> listBooksInOrder = order.getBooks();
        for (int j = 0; j < listBooksInOrder.size(); j++) {
            stringListOfBooksInOrder.append(listBooksInOrder.get(j).getId() + " ");
        }
        stringOfOrder.append(order.getId() + " " + order.getDateOfDoneOrder() + " " + order.getDateOfDoneOrder() + " " + stringListOfBooksInOrder
        + " " + order.getPriceOfOrder() + order.getOrderStatus() + order.getCustomer().getId());
       return string;
    }
}
