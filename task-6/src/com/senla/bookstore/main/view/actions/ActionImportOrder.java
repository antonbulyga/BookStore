package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ActionImportOrder implements IAction {
    @Override
    public void execute() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
            List<Book> listOfBookInOrder = new ArrayList<>();
            List<Customer> customers = CustomerController.getInstance().getListOfCustomers();
        try (BufferedReader reader = new BufferedReader(new FileReader(property.getProperty("orderFile")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                while (true) {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    String dateOfOrder = strings[1];
                    String dateOfDoneOrder = strings[2];
                    String listOfBooks = strings[3];
                    int customerId = Integer.parseInt(strings[4]);
                    double priceOfOrder = Double.parseDouble(strings[5]);
                    String[] idBooksList = listOfBooks.split(" ");
                    for (int i = 0; i <idBooksList.length ; i++) {
                       if(Integer.parseInt(idBooksList[i]) == books.get(i).getId()){
                           listOfBookInOrder.add(books.get(i));
                       }
                    }
                    Order order = OrderController.getInstance().createOrder(listOfBookInOrder, customers.get(customerId), dateOfDoneOrder);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            System.err.println("We have no file");
        }

    }
}
