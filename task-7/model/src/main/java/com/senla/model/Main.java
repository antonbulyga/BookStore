package main.java.com.senla.model;


import main.java.com.senla.config.DIContainer;
import main.java.com.senla.view.MenuController;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        DIContainer diContainer = new DIContainer();
        diContainer.configure();
        MenuController menuController = new MenuController();
        menuController.run();
    }
}
