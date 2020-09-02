package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.enumeration.BookStatus;
import main.java.com.senla.model.utils.generators.BookIdGenerator;
import main.java.com.senla.model.utils.input.DoubleInput;
import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ActionCreateBook implements IAction {

    @Override
    public void execute() throws SQLException {
        String title = null;
        String author = null;
        double price = 0;
        String publicationDateString = null;
        LocalDate publicationDate = null;
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
            System.out.println("Fill in the publication date (dd,MM,yyyy):");
            publicationDateString = StringInput.getStringInput();
            if(publicationDateString.equals(" ")){
                System.out.println("Incorrect input, try again");
                publicationDateString = null;
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        try {
            publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            System.out.println("Incorrect input date");
        }
        LocalDate arriveDate = LocalDate.now();

        Book book = BookController.getInstance().createBook(new Book(BookIdGenerator.getBookId(), author, title, price, BookStatus.IN_STOCK,null, arriveDate, publicationDate));
    }
}
