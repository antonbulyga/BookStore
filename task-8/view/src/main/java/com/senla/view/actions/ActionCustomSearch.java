package main.java.com.senla.view.actions;

import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ActionCustomSearch implements IAction {

    @Override
    public void execute() throws IOException, IllegalAccessException {
        String firstDateString = null;
        LocalDate date = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        String author;
        System.out.println("Enter the publication date. Books with a publication date earlier than this one will be displayed on the screen");
        System.out.println("Please enter the date (dd,MM,yyyy): ");
        while (firstDateString == null){
            System.out.println("Fill in the publication date");
            firstDateString = StringInput.getStringInput();
            if(firstDateString.equals(" ")){
                firstDateString = null;
            }
        }

        try {
            date = LocalDate.parse(firstDateString, dateTimeFormatter);
        }
        catch(DateTimeParseException e){
            System.out.println("Incorrect input date");
        }

        System.out.println("Enter author of the book which your want to print");
        author = StringInput.getStringInput();


        BookController.getInstance().customSearch(author, date);
    }
}
