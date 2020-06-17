package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.сontrollers.RequestForBookController;
import com.senla.bookstore.service.RequestForBookService;
import com.senla.bookstore.view.IAction;

public class ActionShowListOfRequestsForBooks implements IAction {

    @Override
    public void execute() {
        RequestForBookController.getInstance().showListOfRequestsForBooks();
    }
}
