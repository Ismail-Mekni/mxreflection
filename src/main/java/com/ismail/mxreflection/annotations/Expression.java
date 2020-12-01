package com.ismail.mxreflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Expression used with field to be calculated and injected with MXReflection calculator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Expression {

    /**
     * The function to be used with the involved arguments, this function should be written using mXparser function convention
     *
     * @return The function text
     */
    String value() default "";
}
