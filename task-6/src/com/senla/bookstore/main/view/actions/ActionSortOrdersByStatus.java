package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.view.api.IAction;

public class ActionSortOrdersByStatus implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().sortOrdersByStatus();
    }
}
