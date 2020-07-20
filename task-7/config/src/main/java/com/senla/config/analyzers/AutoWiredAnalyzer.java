package main.java.com.senla.config.analyzers;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.Main;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

    public class AutoWiredAnalyzer {
        public void createNewInstance() throws IllegalAccessException, InstantiationException {
            List<Object> listOfInstance = new ArrayList<>();
            Reflections reflections = new Reflections(Main.class.getPackage().getName());
            Set<Class<?>> allClassesWithComponentAnn = reflections.getTypesAnnotatedWith(Component.class);
            for (Class cla : allClassesWithComponentAnn) {
                listOfInstance.add(cla.newInstance());
            }
              for (Class list : allClassesWithComponentAnn) {
                  Field[] fields = list.getDeclaredFields();
                    for (Field declaredField : fields) {
                        if (declaredField.getAnnotations() != null) {
                            if (declaredField.isAnnotationPresent(MyAutoWired.class)) {
                                Type type = declaredField.getType();
                                for (int i = 0; i < listOfInstance.size(); i++) {
                                    Object newObject = listOfInstance.get(i);
                                    for (Type implementationOfInterface : newObject.getClass().getInterfaces()){
                                        if(type.equals(implementationOfInterface)){
                                            declaredField.setAccessible(true);
                                            declaredField.set(declaredField, newObject);
                                            declaredField.setAccessible(false);
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }


}
