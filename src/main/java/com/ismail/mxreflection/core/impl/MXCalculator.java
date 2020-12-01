package com.ismail.mxreflection.core.impl;

import com.ismail.mxreflection.core.Calculator;
import com.ismail.mxreflection.exceptions.AccessNotAllowedToReadException;
import com.ismail.mxreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismail.mxreflection.exceptions.NullFieldValueException;
import com.ismail.mxreflection.models.FieldOrder;
import com.ismail.mxreflection.models.MXFunction;
import com.ismail.mxreflection.parsers.Parser;
import com.ismail.mxreflection.utilities.ReflectionUtility;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
public class MXCalculator<T> implements Calculator<T> {

    private FieldOrder<MXFunction> fieldOrder;

    public MXCalculator(FieldOrder<MXFunction> fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public FieldOrder<MXFunction> getFieldOrder() {
        return fieldOrder;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void calculate(T object) {
        fieldOrder.getOrderedFields().forEach(mxFunction -> calculateFieldValue(mxFunction, object));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void calculate(Collection<T> objects){
        objects.forEach(this::calculate);
    }

    private void calculateFieldValue(MXFunction mxFunction, Object object) {

        Double result = mxFunction.getLambda().apply(getArgumentValues(mxFunction.getArguments(), object));

        writeValueToObjectField(mxFunction.getFieldName(), object, result);
    }

    private Object readValueFromObjectField(String field, Object object) {
        try {
            Object val = ReflectionUtility.getFieldValue(field, object);
            if (val != null)
                return val;
            else
                throw new NullFieldValueException(field);

        } catch (IllegalAccessException e) {
            throw new AccessNotAllowedToReadException(field);
        }
    }

    private void writeValueToObjectField(String field, Object object, Double value) {
            ReflectionUtility.setValueToField(object, field, parseValue(field, object, value));
    }

    private List<Double> getArgumentValues(Set<String> vars, Object object) {
        return Parser.parseArguments(vars.stream().filter(f -> ReflectionUtility.getClassFieldNames(object.getClass()).contains(f))
                .map(f -> readValueFromObjectField(f, object)).collect(Collectors.toList()));
    }

    private Object parseValue(String field, Object object, Double value) {
        try {
            return Parser.parseResult(value, object.getClass().getDeclaredField(field).getType());
        } catch (NoSuchFieldException e) {
            throw new AccessNotAllowedToWriteValueException(field);
        }
    }
}
