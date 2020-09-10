package com.ismail.mathreflection.models;

import com.ismail.mathreflection.utilities.ReflectionUtility;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Formula<T> {


    protected String fieldName;

    protected Set<String> variables;

    protected T function;

    protected Predicate<Double> lambda;

    protected Set<String> extractVariables(String expression, Class clazz) {
        return ReflectionUtility.getClassFieldNames(clazz).stream().filter(expression::contains).collect(Collectors.toSet());
    }

    protected abstract T generateFunction(Set<String> variables, Field field);

    protected abstract void generatePredicate();

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Set<String> getVariables() {
        return variables;
    }

    public void setVariables(Set<String> variables) {
        this.variables = variables;
    }

    public T getFunction() {
        return function;
    }

    public void setFunction(T function) {
        this.function = function;
    }

    public Predicate getLambda() {
        return lambda;
    }

    public void setLambda(Predicate lambda) {
        this.lambda = lambda;
    }
}
