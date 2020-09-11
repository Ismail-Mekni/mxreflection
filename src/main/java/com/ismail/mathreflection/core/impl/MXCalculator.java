package com.ismail.mathreflection.core.impl;

import com.ismail.mathreflection.core.Calculator;
import com.ismail.mathreflection.exceptions.AccessNotAllowedToReadException;
import com.ismail.mathreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismail.mathreflection.models.FieldOrder;
import com.ismail.mathreflection.models.MXFunction;
import com.ismail.mathreflection.utilities.ReflectionUtility;

import java.util.Set;
import java.util.stream.Collectors;


public class MXCalculator<T> implements Calculator<T> {

    private FieldOrder<MXFunction> fieldOrder;

    public MXCalculator() {
    }

    public MXCalculator(FieldOrder<MXFunction> fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public FieldOrder<MXFunction> getFieldOrder() {
        return fieldOrder;
    }

    @Override
    public void calculate(T object) {
        fieldOrder.getOrderedFields().forEach(field -> {
            Set<Double> variables = field.getVariables().stream().filter(f -> ReflectionUtility.getClassFieldNames(object.getClass()).contains(f))
                    .map(f -> {
                        try {
                            return ReflectionUtility.getFieldValue(f, object);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            throw new AccessNotAllowedToReadException(f);
                        }
                    }).collect(Collectors.toSet());

            Double result = field.getLambda().apply(variables);

            try {
                ReflectionUtility.setValueToField(object, field.getFieldName(), result);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new AccessNotAllowedToWriteValueException(field.getFieldName());
            }
        });
    }
}
