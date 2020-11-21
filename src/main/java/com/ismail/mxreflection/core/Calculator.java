package com.ismail.mxreflection.core;

import java.util.Collection;

/**
 * The calculator is used to launch function resolver and result injection
 *
 * @param <T> The type of objects to calculate
 */
public interface Calculator<T> {

    /**
     * Resolve and calculate and inject results in fields with functions
     *
     * @param object object to calculate its declared functions (Note: the object should be of the type used to create and initialize the calculator)
     */
    void calculate (T object);

    /**
     * Resolve and calculate and inject results in fields with functions
     *
     * @param object object to calculate its declared functions (Note: the object should be of the type used to create and initialize the calculator)
     */
    void calculate (Collection<T> object);
}
