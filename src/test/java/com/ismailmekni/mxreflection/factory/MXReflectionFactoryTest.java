package com.ismailmekni.mxreflection.factory;

import com.ismailmekni.mxreflection.beans.*;
import com.ismailmekni.mxreflection.beans.inheritance.ChildCyclicDependencyTestBean;
import com.ismailmekni.mxreflection.core.impl.MXCalculator;
import com.ismailmekni.mxreflection.exceptions.CycleExpressionDependencyException;
import com.ismailmekni.mxreflection.exceptions.DuplicatedArgumentNameOrInnerClassDetectedException;
import com.ismailmekni.mxreflection.exceptions.NotValidExpressionException;
import com.ismailmekni.mxreflection.exceptions.NoExpressionFoundForTypeException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MXReflectionFactoryTest {

    @Test
    public void createExpressionCalculatorTest() {
        MXCalculator calculator = MXFactory.createCalculator(TestBean.class);

        assertNotNull(calculator);
    }

    @Test(expected = NoExpressionFoundForTypeException.class)
    public void throwNoExpressionFoundExceptionWhenCreatingExpressionFactoryTest() {
        MXFactory.createCalculator(WithoutExpressionTestBean.class);

    }

    @Test(expected = NotValidExpressionException.class)
    public void throwExpressionIsNotValidExceptionForFieldTest() {
        MXFactory.createCalculator(InvalidExpressionTestBean.class);
    }

    @Test(expected = CycleExpressionDependencyException.class)
    public void throwCycleExpressionDependencyException() {
        MXFactory.createCalculator(CyclicDependencyTestBean.class);
    }

    @Test(expected = DuplicatedArgumentNameOrInnerClassDetectedException.class)
    public void throwDuplicatedArgumentNameTest(){
        MXFactory.createCalculator(DuplicateNameTestBean.class);
    }

    @Test(expected = CycleExpressionDependencyException.class)
    public void throwCycleExpressionDependencyWithInheritanceException() {
        MXFactory.createCalculator(ChildCyclicDependencyTestBean.class);
    }
}
