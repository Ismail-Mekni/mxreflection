package com.ismail.mxreflection.factory;

import com.ismail.mxreflection.beans.*;
import com.ismail.mxreflection.core.impl.MXCalculator;
import com.ismail.mxreflection.exceptions.CycleFormulaDependencyException;
import com.ismail.mxreflection.exceptions.DuplicatedVariableNameException;
import com.ismail.mxreflection.exceptions.FormulaIsNotValidException;
import com.ismail.mxreflection.exceptions.NoFormulaFoundForTypeException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MXReflectionFactoryTest {

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
