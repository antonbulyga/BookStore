package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.utils.ExportHelper;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.view.api.IAction;

import java.util.List;

public class ActionExportOrder implements IAction {
    @Override
    public void execute() {
        List<Order> orderList = OrderController.getInstance().getListOfOrders();
        ExportHelper.write(orderList, null, null, null, "orderFile");
    }
}
