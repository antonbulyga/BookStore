package main.java.com.senla.config.analyzers;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.config.enumeration.AnnotationEnums;
import main.java.com.senla.model.Main;
import main.java.com.senla.model.utils.PropertyData;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InjectAnalyzer {

    public void setKeyFromAnnotation() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(Component.class);
        List<Object> listOfInstance = new ArrayList<>();
        for (Class cla : allClasses ) {
            listOfInstance.add(cla.newInstance());
        }
        for (int i = 0; i < listOfInstance.size(); i++) {
            Class clazz = listOfInstance.get(i).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MyInject.class)) {
                    MyInject config = field.getAnnotation(MyInject.class);
                    String key = config.key();
                    String pathOfProperty = config.path();
                    String pathByKey = PropertyData.getProperty(key, pathOfProperty);
                    field.setAccessible(true);
                    AnnotationEnums type = config.type();
                    switch(type) {
                        case STRING:
                            field.set(field, pathByKey);
                            break;
                        case INT:
                            field.set(field, Integer.parseInt(pathByKey));
                            break;
                        case BOOLEAN:
                            field.set(field, Boolean.parseBoolean(pathByKey));
                            break;
                    }

                }
            }

        }
    }
}
