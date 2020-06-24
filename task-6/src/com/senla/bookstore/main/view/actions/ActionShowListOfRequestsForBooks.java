package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.сontrollers.RequestForBookController;
import com.senla.bookstore.main.view.api.IAction;

public class ActionShowListOfRequestsForBooks implements IAction {

    @Override
    public void execute() {
        RequestForBookController.getInstance().showListOfRequestsForBooks();
    }
}
