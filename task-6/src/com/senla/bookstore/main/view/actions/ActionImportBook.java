package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.*;
import java.util.Properties;

public class ActionImportBook implements IAction{

    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            try (BufferedReader reader = new BufferedReader(new FileReader(property.getProperty("bookFile")))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    while (true) {
                        String[] strings = line.split(",");
                        int id = Integer.parseInt(strings[0]);
                        String author = strings[1];
                        String title = strings[2];
                        double price = Double.parseDouble(strings[3]);
                        String bookStatus = strings[4];
                        String publicationDate = strings[5];

                        BookController.getInstance().createBook(id, author, title, price, bookStatus, publicationDate);
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
