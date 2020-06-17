package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.сontrollers.BookController;
import com.senla.bookstore.view.IAction;

public class ActionSortBookByAvailabilityInStock implements IAction {

    @Override
    public void execute() {
        BookController.getInstance().sortBookByAvailabilityInStock();
    }
}
