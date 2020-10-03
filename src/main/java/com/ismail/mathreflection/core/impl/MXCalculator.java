package com.ismail.mathreflection.core.impl;

import com.ismail.mathreflection.core.Calculator;
import com.ismail.mathreflection.exceptions.AccessNotAllowedToReadException;
import com.ismail.mathreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismail.mathreflection.exceptions.NullFieldValueException;
import com.ismail.mathreflection.models.FieldOrder;
import com.ismail.mathreflection.models.MXFunction;
import com.ismail.mathreflection.parsers.Parser;
import com.ismail.mathreflection.utilities.ReflectionUtility;

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

        Double result = mxFunction.getLambda().apply(getVariableValues(mxFunction.getVariables(), object));

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
        try {
            ReflectionUtility.setValueToField(object, field, parseValue(field, object, value));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new AccessNotAllowedToWriteValueException(field);
        }
    }

    private List<Double> getVariableValues(Set<String> vars, Object object) {
        return Parser.parseVariables(vars.stream().filter(f -> ReflectionUtility.getClassFieldNames(object.getClass()).contains(f))
                .map(f -> readValueFromObjectField(f, object)).collect(Collectors.toList()));
    }

    private Object parseValue(String field, Object object, Double value) throws NoSuchFieldException {
        return Parser.parseResult(value, object.getClass().getDeclaredField(field).getType());
    }
}
