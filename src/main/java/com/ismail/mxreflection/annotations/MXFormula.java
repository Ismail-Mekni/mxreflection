package com.ismail.mxreflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MXFormula used with field to be calculated and injected with MXReflection calculator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MXFormula {

    /**
     * The function to be used with the involved variables, this function should be written using mXparser function convention
     *
     * @return The function text
     */
    String value() default "";
}
