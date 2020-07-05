package annotation;

import java.lang.reflect.Field;

public class AnnotationAnalyzer {
    public static void setKeyFromAnnotation(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.getAnnotatedType() instanceof Config) {
                String key = field.getAnnotation(Config.class).key();
                field.setAccessible(true);
                field.set(object, key);
                field.setAccessible(false);
            }
        }
    }

    public static void setPathFromAnnotation(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (field.getAnnotatedType() instanceof Config) {
                String path = field.getAnnotation(Config.class).path();
                field.setAccessible(true);
                field.set(object, path);
                field.setAccessible(false);
            }
        }
    }
}
