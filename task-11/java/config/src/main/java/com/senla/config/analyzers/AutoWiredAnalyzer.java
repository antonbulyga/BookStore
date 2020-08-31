package com.senla.config.analyzers;

import com.senla.config.annotations.MyAutoWired;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;


    public class AutoWiredAnalyzer {
        public void createNewInstance(Object object, List<Object> objects) throws IllegalAccessException {
                  Class clazz = object.getClass();
                  Field[] fields = clazz.getDeclaredFields();
                    for (Field declaredField : fields) {
                            if (declaredField.isAnnotationPresent(MyAutoWired.class)) {
                                Type type = declaredField.getType();
                                for (int i = 0; i < objects.size(); i++) {
                                    Object newObject = objects.get(i);
                                    for (Type implementationOfInterface : newObject.getClass().getInterfaces()){
                                        if(type.equals(implementationOfInterface)){
                                            declaredField.setAccessible(true);
                                            declaredField.set(object, newObject);
                                        }
                                }
                            }
                        }
                    }

        }

}
