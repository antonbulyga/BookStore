package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.сontrollers.OrderController;
import com.senla.bookstore.view.IAction;

import java.util.List;

public class ActionSortOrdersByPrice implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().sortOrdersByPrice();
    }
}
