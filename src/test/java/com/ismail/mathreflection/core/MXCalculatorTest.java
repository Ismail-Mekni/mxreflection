package com.ismail.mathreflection.core;

import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.factory.MXFactory;
import com.ismail.mathreflection.models.Formula;
import com.ismail.mathreflection.utilities.BeanTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MXCalculatorTest {

    private MXCalculator calculator;

    @Before
    public void setUp() {
        calculator = MXFactory.createCalculator(BeanTest.class);
    }

    @Test
    public void calculateInitializerTest() {

        assertNotNull(calculator.getFieldOrder());

        assertNotNull(calculator.getFieldOrder().getOrderedFields());

        assertEquals(2, calculator.getFieldOrder().getOrderedFields().size());

        assertEquals("field3", ((Formula) calculator.getFieldOrder().getOrderedFields().poll()).getFieldName());

        assertEquals("field4", ((Formula) calculator.getFieldOrder().getOrderedFields().poll()).getFieldName());

    }

}
