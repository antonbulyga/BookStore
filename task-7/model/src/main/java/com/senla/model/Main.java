package main.java.com.senla.model;


import annotation.AnnotationAnalyzer;
import main.java.com.senla.model.utils.WriteObject;
import main.java.com.senla.view.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        MenuController menuController = new MenuController();
        menuController.run();
    }
}
