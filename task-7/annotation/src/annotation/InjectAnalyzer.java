package annotation;

import main.java.com.senla.model.Main;
import main.java.com.senla.model.utils.PropertyData;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InjectAnalyzer {
    public static void setKeyFromAnnotation() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<? extends Object>> allClassesWithComponentAnn = reflections.getSubTypesOf(Object.class);
        Class[] allClassesWithComponentAnnotationArray = (Class[]) allClassesWithComponentAnn.toArray();
        List<Object> listOfObjects = new ArrayList<>();
        for (int i = 0; i < allClassesWithComponentAnnotationArray.length; i++) {
            Object object = allClassesWithComponentAnnotationArray[i].newInstance();
            listOfObjects.add(object);
        }
        for (int i = 0; i < listOfObjects.size(); i++) {
            Class clazz = listOfObjects.get(i).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MyInject.class)) {
                    MyInject config = field.getAnnotation(MyInject.class);
                    String key = config.key();
                    String pathOfProperty = config.path();
                    String pathByKey = PropertyData.getProperty(key, pathOfProperty);
                    field.setAccessible(true);
                    field.set(field, pathByKey);
                    field.setAccessible(false);
                }
            }

        }
    }
}
