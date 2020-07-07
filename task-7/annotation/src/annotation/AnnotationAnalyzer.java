package annotation;

import main.java.com.senla.model.service.BookServiceImpl;
import main.java.com.senla.model.service.RequestForBookServiceImpl;
import main.java.com.senla.model.utils.PropertyData;
import main.java.com.senla.view.actions.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationAnalyzer {
    public static void setKeyFromAnnotation(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Config.class)) {
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

}
