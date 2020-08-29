package main.java.com.senla.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
    public @interface MyInject {
        String key();
        String path() default "C:/Users/Anton/Documents/bulyha_anton/task-11/java/model/src/main/resources/config.properties";
    }


