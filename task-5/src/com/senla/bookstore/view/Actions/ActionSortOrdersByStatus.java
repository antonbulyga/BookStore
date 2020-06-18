package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.view.api.IAction;

public class ActionSortOrdersByStatus implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().sortOrdersByStatus();
    }
}
