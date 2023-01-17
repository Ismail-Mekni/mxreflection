package com.ismailmekni.mxreflection.factory;

import com.ismailmekni.mxreflection.beans.*;
import com.ismailmekni.mxreflection.beans.inheritance.ChildCyclicDependencyTestBean;
import com.ismailmekni.mxreflection.core.impl.MXCalculator;
import com.ismailmekni.mxreflection.exceptions.CycleExpressionDependencyException;
import com.ismailmekni.mxreflection.exceptions.DuplicatedArgumentNameOrInnerClassDetectedException;
import com.ismailmekni.mxreflection.exceptions.NotValidExpressionException;
import com.ismailmekni.mxreflection.exceptions.NoExpressionFoundForTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MXReflectionFactoryTest {

    @Test
    public void createExpressionCalculatorTest() {
        MXCalculator calculator = MXFactory.createCalculator(TestBean.class);

        assertNotNull(calculator);
    }

    @Test
    public void throwNoExpressionFoundExceptionWhenCreatingExpressionFactoryTest() {
        assertThrows(NoExpressionFoundForTypeException.class, () -> {
            MXFactory.createCalculator(WithoutExpressionTestBean.class);
        });
    }

    @Test
    public void throwExpressionIsNotValidExceptionForFieldTest() {
        assertThrows(NotValidExpressionException.class, () -> {
            MXFactory.createCalculator(InvalidExpressionTestBean.class);
        });
    }

    @Test
    public void throwCycleExpressionDependencyException() {
        assertThrows(CycleExpressionDependencyException.class, () -> {
            MXFactory.createCalculator(CyclicDependencyTestBean.class);
        });
    }

    @Test
    public void throwDuplicatedArgumentNameTest() {
        assertThrows(DuplicatedArgumentNameOrInnerClassDetectedException.class, () -> {
            MXFactory.createCalculator(DuplicateNameTestBean.class);
        });
    }

    @Test
    public void throwCycleExpressionDependencyWithInheritanceException() {
        assertThrows(CycleExpressionDependencyException.class, () -> {
            MXFactory.createCalculator(ChildCyclicDependencyTestBean.class);
        });
    }
}
