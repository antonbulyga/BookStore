package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

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
                System.out.println("Incorrect input, try again");
                indexOfBook = 0;
            }
        }
        OrderController.getInstance().showListOfOrders();
        while (indexOfOrder == 0){
            System.out.println("Fill in index of order");
            indexOfOrder = IntegerInput.getInputInteger();
            if(indexOfOrder < 0){
                System.out.println("Incorrect input, try again");
                indexOfOrder = 0;
            }
        }
        RequestForBookController.getInstance().createRequestForBook(books.get(indexOfBook),orders.get(indexOfOrder));
    }
}
