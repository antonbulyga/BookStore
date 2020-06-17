package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.сontrollers.RequestForBookController;
import com.senla.bookstore.view.IAction;

public class ActionSortRequestByCount implements IAction {

    @Override
    public void execute() {
        RequestForBookController.getInstance().sortRequestByCount();
    }
}
