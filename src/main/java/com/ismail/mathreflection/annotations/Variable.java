package com.ismail.mathreflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Variable annotation to customize the field name to be used with MXFormula annotation
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Variable {

    /**
     * The variable name to be use with MXFormula annotation, one this value is set it should be used in the function instead of the field name
     *
     * @return The variable name
     */
    String value() default "";
}
