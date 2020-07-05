package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Config {
    String key() default "";
    String path() default "C:/Users/Anton/Documents/bulyha_anton/task-7/model/src/main/resources/config.properties";
}
