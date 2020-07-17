package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionCreateOrder implements IAction {

    @Override
    public void execute(){
        List<Book> listOfBooksForOrder = BookController.getInstance().getListOfBooksInStoreHouse();
        List<Customer> listOfCustomers = CustomerController.getInstance().getListOfCustomers();
        int index = 0;
        System.out.println("Choose index of the book for your order from the list of books");
        BookController.getInstance().showBooksInStock();
        while (index == 0){
            index = IntegerInput.getInputInteger();
            if(index < 0){
                System.out.println("Incorrect input, try again");
                index = 0;
            }
        }
        listOfBooksForOrder.add(BookController.getInstance().getListOfBooksInStoreHouse().get(index));
        Order order = OrderController.getInstance().createOrder(listOfBooksForOrder, listOfCustomers.get(0), null);
        OrderController.getInstance().addOrderToListOfOrders(order);
        OrderController.getInstance().addOrderToStore(order);
        OrderController.getInstance().executeOrder(order);
    }
}
