package com.senla.view.actions;

import com.senla.model.сontrollers.OrderController;
import com.senla.view.api.IAction;

public class ActionExportOrder implements IAction {

    @Override
    public void execute() {

        OrderController.getOrderControllerBean().exportOrder();
    }
}
