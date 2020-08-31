package com.senla.view.actions;

import com.senla.model.сontrollers.BookController;
import com.senla.view.api.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    @Override
    public void execute() {
        BookController.getInstance().showUnsoldBooksMoreThanSixMonth();
    }
}
