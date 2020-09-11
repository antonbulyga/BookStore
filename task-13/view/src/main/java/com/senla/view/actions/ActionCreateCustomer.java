package com.senla.view.actions;

import com.senla.model.entity.Customer;
import com.senla.model.utils.input.IntegerInput;
import com.senla.model.utils.input.StringInput;
import com.senla.model.сontrollers.CustomerController;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class ActionCreateCustomer implements IAction {
    static final Logger logger = Logger.getLogger(ActionCreateCustomer.class);
    @Override
    public void execute(){
        int customerAge = 0;
        String customerName = null;
        while (customerAge == 0){
            logger.debug("Fill in the age of the customer");
            customerAge = IntegerInput.getInputInteger();
            if(customerAge < 0){
                logger.error("Incorrect input, try again");
                customerAge = 0;
            }
        }
        while (customerName == null){
            logger.debug("Fill in the name of the customer");
            customerName = StringInput.getStringInput();
            if(customerName.equals(" ")){
                logger.error("Incorrect input, try again");
                customerName = null;
            }
        }
        Customer customer = new Customer(customerAge, customerName);
        try {
            CustomerController.getInstance().addCustomerToListOfCustomers(customer);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}

