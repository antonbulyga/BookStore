package com.senla.view.actions;

import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.utils.input.StringInput;
import com.senla.model.сontrollers.BookController;
import com.senla.model.сontrollers.CustomerController;
import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ActionCreateOrder implements IAction {
    static final Logger logger = Logger.getLogger(ActionCreateOrder.class);
    @Override
    public void execute() {
        List<Book> listOfBooksForOrder = new ArrayList<>();
        List<RequestForBook> requestForBooksForOrder = new ArrayList<>();
        List<Customer> listOfCustomers = CustomerController.getInstance().getListOfCustomers();
        String letter = null;
        String author = null;
        String title = null;
        BookController.getInstance().showBooksInStock();
        BasicConfigurator.configure();
        while (true) {
            logger.debug("Fill in the title of the book for your order from the list of books");
            title = StringInput.getStringInput();
            logger.debug("Fill in the author of the book for your order from the list of books");
            author = StringInput.getStringInput();
            boolean flag = BookController.getInstance().bookInStockChecker(title, author);
            if (flag == true) {
                Book book = BookController.getInstance().getBookByAuthorAndTitle(title, author);
                listOfBooksForOrder.add(book);
            }
            else {
                RequestForBook requestForBook = new RequestForBook(title, author, RequestForBookStatus.ACTIVE,null);
                requestForBooksForOrder.add(requestForBook);
            }
            logger.debug("And than fill in \"e\" if you want to stop. If you don't want to stop fill in another letter ");
            letter = StringInput.getStringInput();
            if(letter.equals("e")){
                break;
            }
        }

        Order order = OrderController.getInstance().createOrder(listOfBooksForOrder, requestForBooksForOrder, listOfCustomers.get(0), null);
        OrderController.getInstance().executeOrder(order);
        OrderController.getInstance().addOrderToListOfOrders(order);


    }
}
