package com.ismail.mathreflection.models;

import com.ismail.mathreflection.utilities.ReflectionUtility;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Formula<T> {


    protected String fieldName;

    protected Set<String> variables;

    protected Set<String> extractVariables(String expression, Class clazz) {
        return ReflectionUtility.getClassFields(clazz).stream().filter(f -> expression.contains(f.getName())).map(Field::getName).collect(Collectors.toSet());
    }

    protected abstract T generateFunction(Set<String> variables, Field field);

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
}
