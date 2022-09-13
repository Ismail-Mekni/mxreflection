package com.ismailmekni.mxreflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Arg annotation to customize the field name to be used with Expression annotation
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Arg {

    /**
     * The argument name to be use with Expression annotation, one this value is set it should be used in the function instead of the field name
     *
     * @return The argument name
     */
    String value() default "";
}
