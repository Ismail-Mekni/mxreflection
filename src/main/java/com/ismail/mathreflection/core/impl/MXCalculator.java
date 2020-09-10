package com.ismail.mathreflection.core.impl;

import com.ismail.mathreflection.core.Calculator;
import com.ismail.mathreflection.models.FieldOrder;
import com.ismail.mathreflection.models.MXFunction;

import java.util.Map;
import java.util.function.Predicate;

public class MXCalculator <T> implements Calculator <T> {

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

    }
}
