package main.java.com.senla.view.actions;

import annotation.Config;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ActionImportOrder implements IAction {
    @Config(key = "orderFile")
    private String path = null;

    @Override
    public void execute() throws IOException {
            List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
            List<Book> listOfBookInOrder = new ArrayList<>();
            List<Order> listOfOrders = OrderController.getInstance().getListOfOrders();
            List<Customer> customers = CustomerController.getInstance().getListOfCustomers();
            try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    String dateOfOrder = strings[1];
                    String dateOfDoneOrderString = strings[2];
                    String listOfBooks = strings[3];
                    int customerId = Integer.parseInt(strings[4]);
                    double priceOfOrder = Double.parseDouble(strings[5]);
                    String[] idBooksList = listOfBooks.split(" ");
                    for (int i = 0; i <idBooksList.length ; i++) {
                       if(Integer.parseInt(idBooksList[i]) == books.get(i).getId()){
                           listOfBookInOrder.add(books.get(i));
                       }
                    }
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
                    LocalDate dateOfDoneOrder = LocalDate.parse(dateOfDoneOrderString, dateTimeFormatter);
                    Order order = OrderController.getInstance().createOrder(listOfBookInOrder, customers.get(customerId), dateOfDoneOrder);
                    for (int i = 0; i < listOfOrders.size(); i++) {
                       if(order.getId() == listOfOrders.get(i).getId()){
                           OrderController.getInstance().updateOrder(order);
                       }
                        else {
                            OrderController.getInstance().addOrderToListOfOrders(order);
                        }
                    }
            }

        } catch (IOException e) {
            System.err.println("We have no file");
        }

    }
}
