package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.model.сontrollers.StockLevelController;
import main.java.com.senla.view.api.IAction;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Component
public class ActionImportBook implements IAction {
    @MyInject(key = "bookFile")
    private String path;

    @Override
    public void execute() {
        List<Book> bookList = BookController.getInstance().getListOfBooksInStoreHouse();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
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
                Book book = BookController.getInstance().createBook(id, title, author, price, publicationDate);
                if (bookList.get(id).getId() == book.getId()) {
                    BookController.getInstance().bookUpdate(book);
                    StockLevelController.getInstance().stockLevelsUpdate(book);

                } else {
                    BookController.getInstance().addBookToListOfBooks(book);
                }
            }
        } catch (IOException e) {
            System.err.println("We have no file");
        }
    }
}

