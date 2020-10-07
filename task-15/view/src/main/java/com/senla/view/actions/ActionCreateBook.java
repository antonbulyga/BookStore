package com.senla.view.actions;

import com.senla.model.entity.Book;
import com.senla.model.utils.BeanGetter;
import com.senla.model.utils.input.DoubleInput;
import com.senla.model.utils.input.StringInput;
import com.senla.model.сontrollers.BookController;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class ActionCreateBook implements IAction {
    static final Logger logger = Logger.getLogger(ActionCreateBook.class);
    @Override
    public void execute() throws SQLException {
        String title = null;
        String author = null;
        double price = 0;
        String publicationDateString = null;
        LocalDate publicationDate = null;
        while (title == null) {
            logger.debug("Fill in the title of the book");
            title = StringInput.getStringInput();
            if(title.equals(" ")){
                logger.error("Incorrect input, try again");
                title = null;
            }
        }
        while (author == null){
            logger.debug("Fill in the author of the book");
            author = StringInput.getStringInput();
            if(author.equals(" ")){
                logger.error("Incorrect input, try again");
                author = null;
            }
        }
        while (price == 0){
            logger.debug("Fill in the price of the book");
            price = DoubleInput.getDoubleInput();
            if(price < 0){
                logger.error("Incorrect input, try again");
                price = 0;
            }
        }
        while (publicationDateString == null){
            logger.debug("Fill in the publication date (dd,MM,yyyy):");
            publicationDateString = StringInput.getStringInput();
            if(publicationDateString.equals(" ")){
                logger.error("Incorrect input, try again");
                publicationDateString = null;
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        try {
            publicationDate = LocalDate.parse(publicationDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            logger.error("Incorrect input date");
        }
        LocalDate arriveDate = LocalDate.now();

        BookController bookController = BeanGetter.getInstance().getBookControllerBean();
        Book book = bookController.createBook(new Book(title, author, price, arriveDate, publicationDate));
    }
}
