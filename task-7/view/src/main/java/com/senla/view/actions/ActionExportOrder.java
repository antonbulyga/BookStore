package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.util.List;
@Component
public class ActionExportOrder implements IAction {
    @MyInject(key = "orderFile")
    private String path;

    @Override
    public void execute() {
        List<Order> orderList = OrderController.getInstance().getListOfOrders();
        ExportHelper.write(orderList, null, null, null, path);
    }
}
