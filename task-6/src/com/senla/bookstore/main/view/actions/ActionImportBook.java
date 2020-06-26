package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.utils.PropertyPath;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActionImportBook implements IAction {

    @Override
    public void execute() {
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PropertyPath.choose("bookFile")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                int id = Integer.parseInt(strings[0]);
                String author = strings[1];
                String title = strings[2];
                double price = Double.parseDouble(strings[3]);
                String bookStatus = strings[4];
                String publicationDateString = strings[5];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
                LocalDate publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);
                if (bookList.get(id).getId() == id) {
                    BookController.getInstance().bookUpdate(id, title, author, price, publicationDate);
                } else {
                    BookController.getInstance().createBook(id, author, title, price, publicationDate);
                }
            }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }
}

