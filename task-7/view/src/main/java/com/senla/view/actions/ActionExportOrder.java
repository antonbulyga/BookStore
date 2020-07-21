package main.java.com.senla.view.actions;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.utils.ExportHelper;
import main.java.com.senla.model.сontrollers.OrderController;
import main.java.com.senla.view.api.IAction;

import java.util.List;

public class ActionExportOrder implements IAction {

    @Override
    public void execute() {
        OrderController.getInstance().exportOrder();
    }
}
