package com.ismail.mxreflection.factory;

import com.ismail.mxreflection.beans.*;
import com.ismail.mxreflection.core.impl.MXCalculator;
import com.ismail.mxreflection.exceptions.CycleExpressionDependencyException;
import com.ismail.mxreflection.exceptions.DuplicatedArgumentNameException;
import com.ismail.mxreflection.exceptions.NotValidExpressionException;
import com.ismail.mxreflection.exceptions.NoExpressionFoundForTypeException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MXReflectionFactoryTest {

    @Test
    public void createExpressionCalculatorTest() {
        MXCalculator calculator = MXFactory.createCalculator(BeanTest.class);

        assertNotNull(calculator);
    }

    @Test(expected = NoExpressionFoundForTypeException.class)
    public void throwNoExpressionFoundExceptionWhenCreatingExpressionFactoryTest() {
        MXFactory.createCalculator(BeanTestWithoutExpression.class);

    }

    @Test(expected = NotValidExpressionException.class)
    public void throwExpressionIsNotValidExceptionForFieldTest() {
        MXFactory.createCalculator(BeanTestInvalidExpression.class);
    }

    @Test(expected = CycleExpressionDependencyException.class)
    public void throwCycleExpressionDependencyException() {
        MXFactory.createCalculator(BeanTestCycleDependency.class);
    }

    @Test(expected = DuplicatedArgumentNameException.class)
    public void throwDuplicatedArgumentNameTest(){
        MXFactory.createCalculator(BeanTestDuplicateName.class);
    }

}
