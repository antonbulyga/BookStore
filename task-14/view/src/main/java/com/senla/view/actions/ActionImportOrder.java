package com.senla.view.actions;

import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;

import java.io.IOException;

public class ActionImportOrder implements IAction {

    @Override
    public void execute() throws IOException {
        OrderController.getOrderControllerBean().importOrder();
    }
}
