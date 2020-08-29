package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionCreateRequestForBook implements IAction {
    @Override
    public void execute(){
        int indexOfOrder = 0;
        List<Order> orders = OrderController.getInstance().getListOfOrders();
        BookController.getInstance().showBooksInStock();
        System.out.println("Input the title of the book that you want to buy");
        String bookTitle = StringInput.getStringInput();
        System.out.println("Input the author of book that you want to buy");
        String bookAuthor = StringInput.getStringInput();
        OrderController.getInstance().showListOfOrders();
        while (indexOfOrder == 0){
            System.out.println("Fill in index of order");
            indexOfOrder = IntegerInput.getInputInteger();
            if(indexOfOrder < 0){
                System.out.println("Incorrect input, try again");
                indexOfOrder = 0;
            }
        }
        RequestForBookController.getInstance().createRequestForBook(bookTitle, bookAuthor,orders.get(indexOfOrder));
    }
}
