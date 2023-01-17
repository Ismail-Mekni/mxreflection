package com.ismailmekni.mxreflection.inheritance;

import com.ismailmekni.mxreflection.beans.inheritance.*;
import com.ismailmekni.mxreflection.core.Calculator;
import com.ismailmekni.mxreflection.exceptions.NotValidExpressionException;
import com.ismailmekni.mxreflection.exceptions.NullFieldValueException;
import com.ismailmekni.mxreflection.exceptions.UnparseableFieldException;
import com.ismailmekni.mxreflection.exceptions.UnparseableResultException;
import com.ismailmekni.mxreflection.factory.MXFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class MxCalculatorWithInheritanceTest {

    @Test
    public void calculateExpressionWhenContainsInheritedArgumentTest() {
        ChildTestBean childTestBean = new ChildTestBean();
        childTestBean.setField1(5);
        childTestBean.setField2(2);

        Calculator<ChildTestBean> calculator = MXFactory.createCalculator(ChildTestBean.class);

        calculator.calculate(childTestBean);

        assertEquals(3, childTestBean.getField3(), 0.001);
        assertEquals(10, childTestBean.getField4(), 0.001);
    }

    @Test
    public void calculateExpressionInParentClassTest() {

        BeanTestChildWithoutExpression beanTestChild = new BeanTestChildWithoutExpression();
        beanTestChild.setField1(5);
        beanTestChild.setField2(2);

        Calculator<BeanTestChildWithoutExpression> calculator = MXFactory.createCalculator(BeanTestChildWithoutExpression.class);

        calculator.calculate(beanTestChild);

        assertEquals(3, beanTestChild.getField3(), 0.001);
        assertEquals(10, beanTestChild.getField4(), 0.001);
    }

    @Test
    public void throwExpressionIsNotValidExceptionForFieldTest() {
        assertThrows(NotValidExpressionException.class, () -> {
            MXFactory.createCalculator(ChildInvalidExpressionTestBean.class);
        });
    }

    @Test
    public void calculateWithAnnotationMix() {
        Calculator<ChildAnnotationMixAndDependencyTestBean> calculatorMix = MXFactory.createCalculator(ChildAnnotationMixAndDependencyTestBean.class);

        ChildAnnotationMixAndDependencyTestBean beanMix = new ChildAnnotationMixAndDependencyTestBean();

        beanMix.field1 = "5";
        beanMix.field2 = "3";
        beanMix.field5 = "6";
        beanMix.field6 = "7";

        calculatorMix.calculate(beanMix);

        assertEquals("19.0", beanMix.field3);
        assertEquals("24.0", beanMix.field4);
        assertEquals("30.0", beanMix.field7);
        assertEquals("11.0", beanMix.field8);
    }

    @Test
    public void calculateThrowExpressionWhenContainsNullInheritedArgumentFieldTest() {
        ChildTestBean childTestBean = new ChildTestBean();
        childTestBean.setField2(2);

        Calculator<ChildTestBean> calculator = MXFactory.createCalculator(ChildTestBean.class);

        assertThrows(NullFieldValueException.class, () -> {
            calculator.calculate(childTestBean);
        });
    }

    @Test
    public void calculateThrowUparseableFieldExceptionTest() {
        Calculator<ChildUnparseableFieldTestBean> calculator = MXFactory.createCalculator(ChildUnparseableFieldTestBean.class);

        ChildUnparseableFieldTestBean beanTest = new ChildUnparseableFieldTestBean();

        beanTest.field1 = new HashMap();

        beanTest.field2 = 6;

        assertThrows(UnparseableFieldException.class, () -> {
            calculator.calculate(beanTest);
        });
    }

    @Test
    public void calculateThrowUnparseableResultExceptionTest() {
        Calculator<ChildUnparseableResultTestBean> calculator = MXFactory.createCalculator(ChildUnparseableResultTestBean.class);

        ChildUnparseableResultTestBean unparseableResult = new ChildUnparseableResultTestBean();


        unparseableResult.field1 = "5";

        unparseableResult.field2 = "6";

        assertThrows(UnparseableResultException.class, () -> {
            calculator.calculate(unparseableResult);
        });
    }
}
