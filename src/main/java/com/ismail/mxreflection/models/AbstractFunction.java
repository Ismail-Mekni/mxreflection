package com.ismail.mxreflection.models;

import com.ismail.mxreflection.core.ReflectionBean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractFunction<T> {


    protected String fieldName;

    protected String argumentName;

    protected Set<String> arguments;

    protected T function;

    protected java.util.function.Function<List<Double>, Double> lambda;

    protected Set<String> extractArguments(String expression, ReflectionBean reflectionBean) {
        return reflectionBean.getClassFieldNames().stream().filter(expression::contains).collect(Collectors.toSet());
    }

    protected abstract T generateFunction(Set<String> arguments, Field field);

    protected abstract void generatePredicate();

    public String getFieldName() {
        return fieldName;
    }

    public String getArgumentName() {
        return argumentName;
    }

    public Set<String> getArguments() {
        return arguments;
    }

    public java.util.function.Function<List<Double>, Double> getLambda() {
        return lambda;
    }

}
