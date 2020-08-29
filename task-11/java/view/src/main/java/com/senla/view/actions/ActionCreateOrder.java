package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.enumeration.RequestForBookStatus;
import main.java.com.senla.model.utils.generators.OrderIdGenerator;
import main.java.com.senla.model.utils.generators.RequestForBookIdGenerator;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

import java.util.ArrayList;
import java.util.List;

public class ActionCreateOrder implements IAction {

    @Override
    public void execute() {
        List<Book> listOfBooksForOrder = new ArrayList<>();
        List<RequestForBook> requestForBooksForOrder = new ArrayList<>();
        List<Customer> listOfCustomers = CustomerController.getInstance().getListOfCustomers();
        String letter = null;
        String author = null;
        String title = null;
        BookController.getInstance().showBooksInStock();
        while (true) {
            System.out.println("Fill in the title of the book for your order from the list of books");
            title = StringInput.getStringInput();
            System.out.println("Fill in the author of the book for your order from the list of books");
            author = StringInput.getStringInput();
            boolean flag = BookController.getInstance().bookInStockChecker(title, author);
            if (flag == true) {
                Book book = BookController.getInstance().getBookByAuthorAndTitle(title, author);
                listOfBooksForOrder.add(book);
            }
            else {
                RequestForBook requestForBook = new RequestForBook(RequestForBookIdGenerator.getRequestForBookId(), title, author, RequestForBookStatus.ACTIVE,null);
                requestForBooksForOrder.add(requestForBook);
            }
            System.out.println("And than fill in \"e\" if you want to stop. If you don't want to stop fill in another letter ");
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
