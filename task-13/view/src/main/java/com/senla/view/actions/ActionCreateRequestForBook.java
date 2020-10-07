package com.senla.view.actions;

import com.senla.model.entity.Order;
import com.senla.model.utils.input.IntegerInput;
import com.senla.model.utils.input.StringInput;
import com.senla.model.сontrollers.BookController;
import com.senla.model.сontrollers.OrderController;
import com.senla.model.сontrollers.RequestForBookController;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.util.List;

public class ActionCreateRequestForBook implements IAction {
    static final Logger logger = Logger.getLogger(ActionCreateRequestForBook.class);
    @Override
    public void execute(){
        int indexOfOrder = 0;
        List<Order> orders = OrderController.getInstance().getListOfOrders();
        BookController.getInstance().showBooksInStock();
        logger.debug("Input the title of the book that you want to buy");
        String bookTitle = StringInput.getStringInput();
        logger.debug("Input the author of book that you want to buy");
        String bookAuthor = StringInput.getStringInput();
        OrderController.getInstance().showListOfOrders();
        while (indexOfOrder == 0){
            logger.debug("Fill in index of order");
            indexOfOrder = IntegerInput.getInputInteger();
            if(indexOfOrder < 0){
                logger.error("Incorrect input, try again");
                indexOfOrder = 0;
            }
        }
        RequestForBookController.getInstance().createRequestForBook(bookTitle, bookAuthor,orders.get(indexOfOrder));
    }
}
