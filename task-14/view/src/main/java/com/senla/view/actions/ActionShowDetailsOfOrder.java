package com.senla.view.actions;

import com.senla.model.entity.Order;
import com.senla.model.utils.BeanGetter;
import com.senla.model.utils.input.IntegerInput;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.util.List;

public class ActionShowDetailsOfOrder implements IAction {
    static final Logger logger = Logger.getLogger(ActionShowDetailsOfOrder.class);
    @Override
    public void execute() {
        int number = 0;
        BeanGetter.getInstance().getOrderControllerBean().showListOfOrders();
        List<Order> listOfOrdersInStore = BeanGetter.getInstance().getOrderControllerBean().getListOfOrders();
        while (number == 0){
            logger.debug("Fill in the number of order");
            number = IntegerInput.getInputInteger();
            if(number < 0){
                number = 0;
            }
        }
        BeanGetter.getInstance().getOrderControllerBean().showDetailsOfOrder(listOfOrdersInStore.get(number));
    }
}
