package main.java.com.senla.config.analyzers;

import main.java.com.senla.config.annotations.MyInject;
import main.java.com.senla.model.utils.PropertyData;

import java.lang.reflect.Field;

public class InjectAnalyzer {

    public void setKeyFromAnnotation(Object object) throws IllegalAccessException, InstantiationException {
            Class clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MyInject.class)) {
                    MyInject config = field.getAnnotation(MyInject.class);
                    String key = config.key();
                    String pathOfProperty = config.path();
                    String pathByKey = PropertyData.getProperty(key, pathOfProperty);
                    field.setAccessible(true);
                    field.set(object, pathByKey);
                }
            }

        }

}
