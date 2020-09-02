package com.senla.config;

import com.senla.config.analyzers.AutoWiredAnalyzer;
import com.senla.config.analyzers.InjectAnalyzer;
import com.senla.config.annotations.Component;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DIContainer {
    private List<Object> listOfInstance = new ArrayList<>();

    public void configure() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.senla.model");
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

    public List<Object> getListOfInstance() {
        return listOfInstance;
    }

    public void setListOfInstance(List<Object> listOfInstance) {
        this.listOfInstance = listOfInstance;
    }
}
