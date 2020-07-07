package main.java.com.senla.view.actions;

import annotation.MyInject;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportOrder implements IAction {
    @MyInject(key = "orderFile")
    private String path = null;

    @Override
    public void execute() {
        List<Order> orderList = OrderController.getInstance().getListOfOrders();
        ExportHelper.write(orderList, null, null, null, path);
    }
}
