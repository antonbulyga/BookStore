package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Book;
import com.senla.bookstore.main.model.utils.generators.BookIdGenerator;
import com.senla.bookstore.main.model.utils.input.DoubleInput;
import com.senla.bookstore.main.model.utils.input.StringInput;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ActionCreateBook implements IAction {

    @Override
    public void execute() throws IOException {
        int id;
        String title = null;
        String author = null;
        double price = 0;
        String publicationDateString = null;
        String bookStatus = "IN_STOCK";
        while (title == null) {
            System.out.println("Fill in the title of the book");
            title = StringInput.getStringInput();
            if(title.equals(" ")){
                System.out.println("Incorrect input, try again");
                title = null;
            }
        }
        while (author == null){
            System.out.println("Fill in the author of the book");
            author = StringInput.getStringInput();
            if(author.equals(" ")){
                System.out.println("Incorrect input, try again");
                author = null;
            }
        }
        while (price == 0){
            System.out.println("Fill in the price of the book");
            price = DoubleInput.getDoubleInput();
            if(price < 0){
                System.out.println("Incorrect input, try again");
                price = 0;
            }
        }
        while (publicationDateString == null){
            System.out.println("Fill in the publication date");
            publicationDateString = StringInput.getStringInput();
            if(publicationDateString.equals(" ")){
                System.out.println("Incorrect input, try again");
                publicationDateString = null;
            }
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        LocalDate publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);

        Book book = BookController.getInstance().createBook(BookIdGenerator.getBookId(), title, author, price, publicationDate);
    }
}
