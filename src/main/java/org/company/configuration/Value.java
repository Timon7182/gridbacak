package org.company.configuration;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Target(ElementType.FIELD)

public @interface Value {

    String name();
    String defaultValue();


}
