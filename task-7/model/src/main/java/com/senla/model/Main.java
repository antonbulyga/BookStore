package main.java.com.senla.model;


import main.java.com.senla.config.DIContainer;
import main.java.com.senla.view.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        DIContainer diContainer = new DIContainer();
        diContainer.configure();
        MenuController menuController = new MenuController();
        menuController.run();
    }
}
