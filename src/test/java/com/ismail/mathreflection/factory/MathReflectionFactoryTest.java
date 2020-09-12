package com.ismail.mathreflection.factory;

import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.exceptions.CycleFormulaDependencyException;
import com.ismail.mathreflection.exceptions.DuplicatedVariableNameException;
import com.ismail.mathreflection.exceptions.FormulaIsNotValidException;
import com.ismail.mathreflection.exceptions.NoFormulaFoundForTypeException;
import com.ismail.mathreflection.utilities.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MathReflectionFactoryTest {

    @Test
    public void createFormulaCalculatorTest() {
        MXCalculator calculator = MXFactory.createCalculator(BeanTest.class);

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

    @Test(expected = DuplicatedVariableNameException.class)
    public void throwDuplicatedVariableNameTest(){
        MXFactory.createCalculator(BeanTestDuplicateName.class);
    }
}
