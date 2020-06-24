package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.utils.BookStringForExportBuilder;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class ActionExportBook implements IAction {

    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(property.getProperty("bookFile")))) {
                for (int i = 0; i < bookList.size(); i++) {
                    writer.write(BookStringForExportBuilder.bookStringBuilder(bookList.get(i)) + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    } catch (IOException e) {
        System.err.println("We have no file");
    }

}

}
