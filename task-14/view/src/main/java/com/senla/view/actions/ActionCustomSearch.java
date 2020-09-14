package com.senla.view.actions;

import com.senla.model.utils.BeanGetter;
import com.senla.model.utils.input.StringInput;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ActionCustomSearch implements IAction {
    static final Logger logger = Logger.getLogger(ActionCustomSearch.class);
    @Override
    public void execute() {
        String firstDateString = null;
        LocalDate date = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        String author;
        logger.debug("Enter the publication date. Books with a publication date earlier than this one will be displayed on the screen");
        logger.debug("Please enter the date (dd,MM,yyyy): ");
        while (firstDateString == null){
            logger.debug("Fill in the publication date");
            firstDateString = StringInput.getStringInput();
            if(firstDateString.equals(" ")){
                firstDateString = null;
            }
        }

        try {
            date = LocalDate.parse(firstDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            logger.debug("Incorrect input date");
        }

        logger.debug("Enter author of the book which your want to print");
        author = StringInput.getStringInput();


        BeanGetter.getInstance().getBookControllerBean().customSearch(author, date);
    }
}
