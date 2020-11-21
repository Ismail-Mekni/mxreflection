package com.ismail.mxreflection.models;

import com.ismail.mxreflection.utilities.ReflectionUtility;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Formula<T> {


    protected String fieldName;

    protected Set<String> variables;

    protected T function;

    protected Function<List<Double>, Double> lambda;

    protected Set<String> extractVariables(String expression, Class clazz) {
        return ReflectionUtility.getClassFieldNames(clazz).stream().filter(expression::contains).collect(Collectors.toSet());
    }

    protected abstract T generateFunction(Set<String> variables, Field field);

    protected abstract void generatePredicate();

    public String getFieldName() {
        return fieldName;
    }

    public Set<String> getVariables() {
        return variables;
    }

    public Function<List<Double>, Double> getLambda() {
        return lambda;
    }

}
