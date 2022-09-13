package com.ismailmekni.mxreflection.core.impl;

import com.ismailmekni.mxreflection.core.Calculator;
import com.ismailmekni.mxreflection.exceptions.AccessNotAllowedToReadException;
import com.ismailmekni.mxreflection.exceptions.NullFieldValueException;
import com.ismailmekni.mxreflection.models.FieldOrder;
import com.ismailmekni.mxreflection.models.MXFunction;
import com.ismailmekni.mxreflection.parsers.Parser;
import com.ismailmekni.mxreflection.core.ReflectionBean;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
public class MXCalculator<T> implements Calculator<T> {

    private FieldOrder<MXFunction> fieldOrder;

    private ReflectionBean reflectionBean;

    public MXCalculator(FieldOrder<MXFunction> fieldOrder, ReflectionBean reflectionBean) {
        this.fieldOrder = fieldOrder;
        this.reflectionBean = reflectionBean;
    }

    public FieldOrder<MXFunction> getFieldOrder() {
        return fieldOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void calculate(T object) {
        fieldOrder.getOrderedFields().forEach(mxFunction -> calculateFieldValue(mxFunction, object));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void calculate(Collection<T> objects) {
        objects.forEach(this::calculate);
    }

    private void calculateFieldValue(MXFunction mxFunction, Object object) {

        Double result = mxFunction.getLambda().apply(getArgumentValues(mxFunction.getArguments(), object));

        writeValueToObjectField(mxFunction.getFieldName(), object, result);
    }

    private Object readValueFromObjectField(String field, Object object) {
        try {
            Object val = reflectionBean.getFieldValue(field, object);
            if (val == null)
                throw new NullFieldValueException(field);

            return val;

        } catch (IllegalAccessException e) {
            throw new AccessNotAllowedToReadException(field);
        }
    }

    private void writeValueToObjectField(String field, Object object, Double value) {
        reflectionBean.setValueToField(object, field, parseValue(field, value));
    }

    private List<Double> getArgumentValues(Set<String> vars, Object object) {
        return Parser.parseArguments(vars.stream().filter(f -> reflectionBean.getClassFieldNames().contains(f))
                .map(f -> readValueFromObjectField(f, object)).collect(Collectors.toList()));
    }

    private Object parseValue(String field, Double value) {
        return Parser.parseResult(value, reflectionBean.getFieldTypeByName(field));
    }
}
