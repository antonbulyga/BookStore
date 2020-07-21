package main.java.com.senla.config;

import main.java.com.senla.config.analyzers.AutoWiredAnalyzer;
import main.java.com.senla.config.analyzers.InjectAnalyzer;
import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.Main;
import main.java.com.senla.model.сontrollers.*;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DIContainer {
    private List<Object> listOfInstance;
    public DIContainer(){
        listOfInstance = new ArrayList<>();
        listOfInstance.add(BookController.getInstance());
        listOfInstance.add(CustomerController.getInstance());
        listOfInstance.add(OrderController.getInstance());
        listOfInstance.add(RequestForBookController.getInstance());
        listOfInstance.add(StockLevelController.getInstance());
    }
    public void configure() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<?>> allClassesWithComponentAnn = reflections.getTypesAnnotatedWith(Component.class);
        AutoWiredAnalyzer autoWiredAnalyzer = new AutoWiredAnalyzer();
        InjectAnalyzer injectAnalyzer = new InjectAnalyzer();
        for (Class cla : allClassesWithComponentAnn){
            listOfInstance.add(cla.newInstance());
        }
        for (int i = 0; i < listOfInstance.size(); i++) {
            autoWiredAnalyzer.createNewInstance(listOfInstance.get(i), listOfInstance);
            injectAnalyzer.setKeyFromAnnotation(listOfInstance.get(i));
        }

    }
}
