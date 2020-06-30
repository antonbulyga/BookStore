package main.java.com.senla.view.actions;

import main.java.com.senla.model.entity.Customer;
import main.java.com.senla.model.utils.generators.CustomerIdGenerator;
import main.java.com.senla.model.utils.input.IntegerInput;
import main.java.com.senla.model.utils.input.StringInput;
import main.java.com.senla.model.сontrollers.CustomerController;
import main.java.com.senla.view.api.IAction;

public class ActionCreateCustomer implements IAction {

    @Override
    public void execute(){
        int customerAge = 0;
        String customerName = null;

        while (customerAge == 0){
            System.out.println("Fill in the age of the customer");
            customerAge = IntegerInput.getInputInteger();
            if(customerAge < 0){
                System.out.println("Incorrect input, try again");
                customerAge = 0;
            }
        }
        while (customerName == null){
            System.out.println("Fill in the name of the customer");
            customerName = StringInput.getStringInput();
            if(customerName.equals(" ")){
                System.out.println("Incorrect input, try again");
                customerName = null;
            }
        }
        Customer customer = new Customer(CustomerIdGenerator.getCustomerId(), customerAge, customerName);
        CustomerController.getInstance().addCustomerToListOfCustomers(customer);
    }
}

