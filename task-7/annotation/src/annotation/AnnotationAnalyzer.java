package annotation;

import main.java.com.senla.model.utils.PropertyData;

import java.lang.reflect.Field;

public class AnnotationAnalyzer {
    public static void setKeyFromAnnotation(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.getAnnotatedType() instanceof Config) {
                Config config = field.getAnnotation(Config.class);
                String key = config.key();
                String pathOfProperty = config.path();
                String pathByKey = PropertyData.getProperty(key, pathOfProperty);
                field.setAccessible(true);
                field.set(object, pathByKey);
                field.setAccessible(false);
            }
        }
    }

}
