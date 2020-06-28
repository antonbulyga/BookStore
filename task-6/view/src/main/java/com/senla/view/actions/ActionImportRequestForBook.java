package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.RequestForBook;
import main.java.com.senla.model.utils.PropertyPath;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ActionImportRequestForBook implements IAction {
    @Override
    public void execute(){
        try {
        List<Order> orders = OrderController.getInstance().getListOfOrders();
        List<Book> books = BookController.getInstance().getListOfBooksInStoreHouse();
        List<RequestForBook> requestForBooks = RequestForBookController.getInstance().getListOfRequestForBook();
        BufferedReader reader = new BufferedReader(new FileReader(PropertyPath.choose("requestForBookFile")));
            String line;
            while ((line = reader.readLine()) != null) {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    int bookId = Integer.parseInt(strings[1]);
                    String requestForBookStatus = strings[2];
                    int orderId = Integer.parseInt(strings[3]);
                    RequestForBook requestForBook = RequestForBookController.getInstance().createRequestForBook(books.get(bookId),orders.get(orderId));
                for (int i = 0; i < requestForBooks.size(); i++) {
                    if(requestForBook.getId() == requestForBooks.get(i).getId()){
                        RequestForBookController.getInstance().updateRequestForBook(requestForBook);
                    }
                    else {
                        RequestForBookController.getInstance().addRequestForBookToList(requestForBook);
                    }
                }

                }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }

}
