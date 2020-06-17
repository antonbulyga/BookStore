package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.RequestForBook;
import com.senla.bookstore.model.StockLevel;
import com.senla.bookstore.model.сontrollers.BookController;
import com.senla.bookstore.model.сontrollers.StockLevelController;
import com.senla.bookstore.model.сontrollers.StoreController;
import com.senla.bookstore.service.StoreService;
import com.senla.bookstore.view.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActionCreateBook implements IAction {

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Fill in the id of the book");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("Fill in the title of the book");
        String title = reader.readLine();
        System.out.println("Fill in the author of the book");
        String author = reader.readLine();
        System.out.println("Fill in the price of the book");
        double price = Double.parseDouble(reader.readLine());
        BookStatus bookStatus = BookStatus.IN_STOCK;
        List<RequestForBook> requestForBooks = new ArrayList<>();
        LocalDate arriveDate = LocalDate.now();
        System.out.println("Fill in the publication date");
        String publicationDate = reader.readLine();
        int yearOfPublication = Integer.parseInt(publicationDate.substring(0,4));
        int monthOfPublication = Integer.parseInt(publicationDate.substring(5,7));
        int dayOfPublication = Integer.parseInt(publicationDate.substring(8,10));
        LocalDate dateOfPublication = LocalDate.of(yearOfPublication, monthOfPublication, dayOfPublication);
        Book book = new Book(id, title, author, price, bookStatus, requestForBooks, arriveDate, dateOfPublication);
        BookController.getInstance().addBookToListOfBooks(book);

        StockLevel stockLevel = new StockLevel(book, 0);
        List<StockLevel> stockLevels = StockLevelController.getInstance().getArrayOfStockLevels();
        stockLevels.add(stockLevel);
        StockLevelController.getInstance().setArrayOfStockLevels(stockLevels);
        StoreController.getInstance().arriveBookToStock(book);
        StoreController.getInstance().completingRequestAfterArrivingNewBook(book);

    }
}
