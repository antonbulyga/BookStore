package com.senla.main;


import com.senla.config.DIContainer;
import com.senla.model.DAO.MysqlConnect;
import com.senla.model.сontrollers.BookController;
import com.senla.model.сontrollers.CustomerController;
import com.senla.model.сontrollers.OrderController;
import com.senla.model.сontrollers.RequestForBookController;
import com.senla.view.MenuController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        DIContainer diContainer = new DIContainer();
        List<Object> listOfInstance = new ArrayList<>();
        listOfInstance.add(MysqlConnect.getInstance());
        listOfInstance.add(BookController.getInstance());
        listOfInstance.add(CustomerController.getInstance());
        listOfInstance.add(OrderController.getInstance());
        listOfInstance.add(RequestForBookController.getInstance());
        diContainer.setListOfInstance(listOfInstance);
        diContainer.configure();
        MenuController menuController = new MenuController();
        menuController.run();
    }
}
