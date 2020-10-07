package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.сontrollers.RequestForBookController;
import com.senla.bookstore.view.api.IAction;

public class ActionSortRequestByAlphabet implements IAction {

    @Override
    public void execute() {
        RequestForBookController.getInstance().sortRequestByAlphabet();
    }

}
