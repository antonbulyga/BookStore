package com.senla.view.actions;

import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;

public class ActionSortOrdersByStatus implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().sortOrdersByStatus();
    }
}
