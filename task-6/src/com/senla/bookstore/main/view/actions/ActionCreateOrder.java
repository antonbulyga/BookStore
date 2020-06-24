package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.utils.IntegerInput;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.model.сontrollers.StoreController;
import com.senla.bookstore.main.view.api.IAction;

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
                index = 0;
            }
        }
        listOfBooksForOrder.add(BookController.getInstance().getListOfBooksInStoreHouse().get(index));
        Order order = OrderController.getInstance().createOrder(listOfBooksForOrder, listOfCustomers.get(0), null);
        OrderController.getInstance().addOrderToListOfOrders(order);
        StoreController.getInstance().addOrderToStore(order);
        StoreController.getInstance().executeOrder(order);
    }
}
