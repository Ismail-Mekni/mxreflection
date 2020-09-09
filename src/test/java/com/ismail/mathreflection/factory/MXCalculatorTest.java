package com.ismail.mathreflection.factory;

import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.utilities.BeanTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MXCalculatorTest {

    private MXCalculator calculator;

    @Before
    public void setUp() {
        calculator = MXFactory.createCalculator(BeanTest.class);
    }

    @Test
    public void calculateFormulaTest() {

        assertNotNull(calculator.getFieldOrder());

        assertNotNull(calculator.getFieldOrder().getOrderedFields());

        assertEquals(2, calculator.getFieldOrder().getOrderedFields().size());

        assertEquals("field3", calculator.getFieldOrder().getOrderedFields().poll().getFieldName());

        assertEquals("field4", calculator.getFieldOrder().getOrderedFields().poll().getFieldName());

    }
}
