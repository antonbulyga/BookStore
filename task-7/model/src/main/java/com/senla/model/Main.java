package main.java.com.senla.model;


import main.java.com.senla.config.analyzers.AutoWiredAnalyzer;
import main.java.com.senla.config.analyzers.InjectAnalyzer;
import main.java.com.senla.view.MenuController;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AutoWiredAnalyzer autoWiredAnalyzer = new AutoWiredAnalyzer();
        autoWiredAnalyzer.createNewInstance();
        InjectAnalyzer injectAnalyzer = new InjectAnalyzer();
        injectAnalyzer.setKeyFromAnnotation();

        MenuController menuController = new MenuController();
        menuController.run();
    }
}
