package annotation;

import main.java.com.senla.model.Main;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;

public class AutoWiredAnalyzer {
    public static void createNewInstance() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(Component.class);
        Class[] allClassesArray = (Class[])allClasses.toArray();
        for (int i = 0; i < allClassesArray.length; i++) {
            Class clazz = allClassesArray[i].getClass();
            Field[] fields = clazz.getDeclaredFields();
            if (clazz.isInterface()) {
                for (Field declaredField : fields) {
                    if (declaredField.getAnnotations() != null) {
                        if (declaredField.isAnnotationPresent(MyAutoWired.class)) {
                            Set<Class<? extends clazz>> classSet = reflections.getSubTypesOf(clazz);
                            Class[] f = (Class[]) classSet.toArray();
                            declaredField.setAccessible(true);
                            declaredField.set(declaredField, f[0].newInstance());
                            declaredField.setAccessible(false);
                        }
                    }
                }
            }
        }
    }
}
