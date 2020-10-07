package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.сontrollers.BookController;
import com.senla.bookstore.view.api.IAction;

public class ActionSortBookByAuthor implements IAction {

    @Override
    public void execute() {
        BookController.getInstance().sortBookByAuthor();
    }
}
