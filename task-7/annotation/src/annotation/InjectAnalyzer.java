package annotation;

import main.java.com.senla.model.Main;
import main.java.com.senla.model.service.BookServiceImpl;
import main.java.com.senla.model.service.RequestForBookServiceImpl;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.view.actions.*;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;

public class AnnotationAnalyzer {
    public static void setKeyFromAnnotation(Object object) throws IllegalAccessException {
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
                field.setAccessible(false);
           }
        }

    }

    public static void initializer() throws IllegalAccessException {
        ActionExportBook actionExportBook = new ActionExportBook();
        setKeyFromAnnotation(actionExportBook);
        ActionExportCustomer actionExportCustomer = new ActionExportCustomer();
        setKeyFromAnnotation(actionExportCustomer);
        ActionExportOrder actionExportOrder = new ActionExportOrder();
        setKeyFromAnnotation(actionExportOrder);
        ActionExportRequestForBook actionExportRequestForBook = new ActionExportRequestForBook();
        setKeyFromAnnotation(actionExportRequestForBook);
        ActionImportBook actionImportBook = new ActionImportBook();
        setKeyFromAnnotation(actionImportBook);
        ActionImportOrder actionImportOrder = new ActionImportOrder();
        setKeyFromAnnotation(actionImportOrder);
        ActionImportCustomer actionImportCustomer = new ActionImportCustomer();
        setKeyFromAnnotation(actionImportCustomer);
        ActionImportRequestForBook actionImportRequestForBook = new ActionImportRequestForBook();
        setKeyFromAnnotation(actionImportRequestForBook);
        setKeyFromAnnotation(BookServiceImpl.getInstance());
        setKeyFromAnnotation(RequestForBookServiceImpl.getInstance());
    }

    public static Object createNewInstance(Object object) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<? extends Object>> allClasses =  reflections.getSubTypesOf(Object.class);
        Class[] allClassesArray = (Class[])allClasses.toArray();
        for (int i = 0; i < allClassesArray.length; i++) {
            Class clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            if (clazz.isInterface()) {
                for (Field declaredField : fields) {
                    if (declaredField.getAnnotations() != null) {
                        if (declaredField.isAnnotationPresent(MyAutoWired.class)) {
                            Set<Class<? extends clazz>> classSet = reflections.getSubTypesOf(clazz);
                            Class[] f = (Class[]) classSet.toArray();
                            declaredField.setAccessible(true);
                            declaredField.set(object, f[0].newInstance());
                            declaredField.setAccessible(false);
                        }
                    }
                }
            }
        }
        return object;
    }

}
