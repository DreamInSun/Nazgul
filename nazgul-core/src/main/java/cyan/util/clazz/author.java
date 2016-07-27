package cyan.util.clazz;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation:见http://blog.csdn.net/sodino/article/details/7987888
 * */
@Target(ElementType.TYPE)//ElementType.TYPE用于标识类、接口(包括内注自身)、枚举
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface author {
    //修饰符仅可为public, protected, private & static的组合
    public static enum AppEnum {
        Web, Client, Service, Undesignated
    };

    //public & abstract的组合或默认
    AppEnum type() default AppEnum.Undesignated;

    String name() default "unknown";

    String webSite() default "N/A";
}
