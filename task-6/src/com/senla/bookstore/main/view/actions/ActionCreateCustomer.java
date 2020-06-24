package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.entity.Customer;
import com.senla.bookstore.main.model.utils.IntegerInput;
import com.senla.bookstore.main.model.utils.StringInput;
import com.senla.bookstore.main.model.сontrollers.BookController;
import com.senla.bookstore.main.model.сontrollers.CustomerController;
import com.senla.bookstore.main.view.api.IAction;

public class ActionCreateCustomer implements IAction {

    @Override
    public void execute(){
        int customerId;
        int customerAge = 0;
        String customerName = null;

        if(BookController.getInstance().getListOfBooksInStoreHouse() != null){
            customerId = CustomerController.getInstance().getListOfCustomers().size() + 1;
        }
        else {
            customerId = 0;
        }
        while (customerAge == 0){
            System.out.println("Fill in the age of the customer");
            customerAge = IntegerInput.getInputInteger();
            if(customerAge < 0){
                customerAge = 0;
            }
        }
        while (customerName == null){
            System.out.println("Fill in the name of the customer");
            customerName = StringInput.getStringInput();
            if(customerName.equals(" ")){
                customerName = null;
            }
        }
        Customer customer = new Customer(customerId, customerAge, customerName);
        CustomerController.getInstance().addCustomerToListOfCustomers(customer);
    }
}

