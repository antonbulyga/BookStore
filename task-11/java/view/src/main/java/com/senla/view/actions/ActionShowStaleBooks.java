package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.BookController;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;

public class ActionShowStaleBooks implements IAction {
    @Override
    public void execute() throws IOException {
        BookController.getInstance().showStaleBooks();
    }
}
