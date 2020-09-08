package com.ismail.mathreflection.factory;

import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.utilities.BeanTest;
import org.junit.Before;
import org.junit.Test;

public class MXCalculatorTest {

    private MXCalculator calculator;

    @Before
    public void setUp() {
        calculator=MXFactory.createCalculator(BeanTest.class);
    }

    @Test
    public void calculateFormulaTest(){

    }
}
