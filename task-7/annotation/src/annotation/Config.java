package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface Config {
    String key();
    String path() default "C:/Users/Anton/Documents/bulyha_anton/task-7/model/src/main/resources/config.properties";
}
