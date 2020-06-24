package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.view.api.IAction;

public class ActionSortBookByAvailabilityInStock implements IAction {

    @Override
    public void execute() {
        BookController.getInstance().sortBookByAvailabilityInStock();
    }
}
