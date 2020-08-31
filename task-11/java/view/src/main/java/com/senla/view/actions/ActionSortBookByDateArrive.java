package com.senla.view.actions;

import com.senla.model.сontrollers.BookController;
import com.senla.view.api.IAction;


public class ActionSortBookByDateArrive implements IAction {

    @Override
    public void execute() {
        BookController.getInstance().sortBookByDateArrive();
    }
}
