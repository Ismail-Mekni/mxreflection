package com.ismail.mathreflection.core;

import java.util.Collection;

public interface Calculator<T> {

    void calculate (T object);

    void calculate (Collection<T> object);
}
