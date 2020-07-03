package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    @Override
    public void execute() {
        BookController.getInstance().showUnsoldBooksMoreThanSixMonth();
    }
}
