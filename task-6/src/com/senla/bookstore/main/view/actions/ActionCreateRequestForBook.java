package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.utils.IntegerInput;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.model.сontrollers.RequestForBookController;
import com.senla.bookstore.main.view.api.IAction;

import java.util.List;

public class ActionCreateRequestForBook implements IAction {
    @Override
    public void execute(){
        int indexOfBook = 0;
        int indexOfOrder = 0;
        List<Order> orders = OrderController.getInstance().getListOfOrders();
        List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
        BookController.getInstance().showBooksInStock();
        while (indexOfBook == 0){
            System.out.println("Fill in index of book");
            indexOfBook = IntegerInput.getInputInteger();
            if(indexOfBook < 0){
                indexOfBook = 0;
            }
        }
        OrderController.getInstance().showListOfOrders();
        while (indexOfOrder == 0){
            System.out.println("Fill in index of order");
            indexOfOrder = IntegerInput.getInputInteger();
            if(indexOfOrder < 0){
                indexOfOrder = 0;
            }
        }
        RequestForBookController.getInstance().createRequestForBook(books.get(indexOfBook),orders.get(indexOfOrder));
    }
}
