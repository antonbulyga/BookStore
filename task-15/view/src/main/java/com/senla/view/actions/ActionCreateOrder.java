package com.senla.view.actions;

import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.entity.RequestForBook;
import com.senla.model.enumeration.RequestForBookStatus;
import com.senla.model.utils.BeanGetter;
import com.senla.model.utils.input.StringInput;
import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ActionCreateOrder implements IAction {
    static final Logger logger = Logger.getLogger(ActionCreateOrder.class);
    @Override
    public void execute() {
        List<Book> listOfBooksForOrder = new ArrayList<>();
        List<RequestForBook> requestForBooksForOrder = new ArrayList<>();
        List<Customer> listOfCustomers = BeanGetter.getInstance().getCustomerControllerBean().getListOfCustomers();
        String letter = null;
        String author = null;
        String title = null;
        BeanGetter.getInstance().getBookControllerBean().showBooksInStock();
        while (true) {
            logger.info("Fill in the title of the book for your order from the list of books");
            title = StringInput.getStringInput();
            logger.info("Fill in the author of the book for your order from the list of books");
            author = StringInput.getStringInput();
            boolean flag = BeanGetter.getInstance().getBookControllerBean().bookInStockChecker(title, author);
            if (flag == true) {
                Book book = BeanGetter.getInstance().getBookControllerBean().getBookByAuthorAndTitle(title, author);
                listOfBooksForOrder.add(book);
            }
            else {
                RequestForBook requestForBook = new RequestForBook(title, author, RequestForBookStatus.ACTIVE,null);
                requestForBooksForOrder.add(requestForBook);
            }
            logger.info("And than fill in \"e\" if you want to stop. If you don't want to stop fill in another letter ");
            letter = StringInput.getStringInput();
            if(letter.equals("e")){
                break;
            }
        }

        OrderController orderController = BeanGetter.getInstance().getOrderControllerBean();
        Order order = orderController.createOrder(listOfBooksForOrder, requestForBooksForOrder, listOfCustomers.get(0), null);
        orderController.executeOrder(order);
    }
}
