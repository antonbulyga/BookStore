package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.util.ArrayList;
import java.util.List;

public class ActionCreateOrder implements IAction {

    @Override
    public void execute() {
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
        List<Book> listOfBooksForOrder = new ArrayList<>();
        List<Customer> listOfCustomers = CustomerController.getInstance().getListOfCustomers();
        int index = 0;
        String letter = null;

        BookController.getInstance().showBooksInStock();
        System.out.println("Choose the index of the book for your order from the list of books");
        System.out.println("And than fill in \"e\" if you want to stop. If you don't want to stop fill in another letter ");

            while (index == 0) {
                index = IntegerInput.getInputInteger();
                if (index < 0) {
                    System.out.println("Incorrect input, try again");
                    index = 0;
                }
                listOfBooksForOrder.add(bookList.get(index));
                index = 0;
                letter = StringInput.getStringInput();
                if(letter.equals("e")){
                    break;
                }
            }


        Order order = OrderController.getInstance().createOrder(listOfBooksForOrder, listOfCustomers.get(0), null);
        OrderController.getInstance().addOrderToListOfOrders(order);
        OrderController.getInstance().addOrderToStore(order);
        OrderController.getInstance().executeOrder(order);

    }
}
