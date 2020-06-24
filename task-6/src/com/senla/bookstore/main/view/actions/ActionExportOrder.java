package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.utils.OrderStringForExportBuilder;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.view.api.IAction;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ActionExportOrder implements IAction {

    @Override
    public void execute(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            List<Order> orderList = OrderController.getInstance().getListOfOrders();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(property.getProperty("orderFile")))) {
                for (int i = 0; i < orderList.size(); i++) {
                    writer.write(OrderStringForExportBuilder.orderStringBuilder(orderList.get(i)) + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


    } catch (IOException e) {
        System.err.println("We have no file");
    }
    }
}
