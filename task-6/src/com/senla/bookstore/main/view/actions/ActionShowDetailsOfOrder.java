package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Order;
import com.senla.bookstore.main.model.utils.input.IntegerInput;
import com.senla.bookstore.main.model.сontrollers.OrderController;
import com.senla.bookstore.main.model.service.StoreService;
import com.senla.bookstore.main.view.api.IAction;

import java.io.IOException;
import java.util.List;

public class ActionShowDetailsOfOrder implements IAction {

    @Override
    public void execute() throws IOException {
        int number = 0;
        OrderController.getInstance().showListOfOrders();
        List<Order> listOfOrdersInStore = StoreService.getInstance().getStore().getListOfOrders();
        while (number == 0){
            System.out.println("Fill in the number of order");
            number = IntegerInput.getInputInteger();
            if(number < 0){
                number = 0;
            }
        }
        OrderController.getInstance().showDetailsOfOrder(listOfOrdersInStore.get(number));
    }
}
