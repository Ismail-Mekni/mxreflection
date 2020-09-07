package com.ismail.mathreflection.factory;

import com.ismail.mathreflection.core.Calculator;
import com.ismail.mathreflection.exceptions.CycleFormulaDependencyException;
import com.ismail.mathreflection.exceptions.FormulaIsNotValidException;
import com.ismail.mathreflection.exceptions.NoFormulaFoundForTypeException;
import com.ismail.mathreflection.utilities.BeanTest;
import com.ismail.mathreflection.utilities.BeanTestCycleDependency;
import com.ismail.mathreflection.utilities.BeanTestInvalidFormula;
import com.ismail.mathreflection.utilities.BeanTestWithoutFormula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MathReflectionFactoryTest {

    @Before
    public void setUp() {

    }

    @Test
    public void createFormulaCalculatorTest() {
        Calculator calculator = MXFactory.createCalculator(BeanTest.class);

        assertNotNull(calculator);
    }

    @Test(expected = NoFormulaFoundForTypeException.class)
    public void throwNoFormulaFoundExceptionWhenCreatingFormulaFactoryTest() {
        MXFactory.createCalculator(BeanTestWithoutFormula.class);

    }

    @Test(expected = FormulaIsNotValidException.class)
    public void throwFormulaIsNotValidExceptionForFieldTest() {
        MXFactory.createCalculator(BeanTestInvalidFormula.class);
    }

    @Test(expected = CycleFormulaDependencyException.class)
    public void throwCycleFormulaDependencyException() {
        MXFactory.createCalculator(BeanTestCycleDependency.class);
    }
}
