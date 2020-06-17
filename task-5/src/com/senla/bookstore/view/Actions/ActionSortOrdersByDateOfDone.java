package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.view.IAction;

public class ActionSortOrdersByDateOfDone implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().sortOrdersByDateOfDone();
    }
}
