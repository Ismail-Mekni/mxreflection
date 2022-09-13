package com.ismailmekni.mxreflection.inheritance;

import com.ismailmekni.mxreflection.beans.inheritance.ChildTestBean;
import com.ismailmekni.mxreflection.beans.inheritance.ChildInvalidExpressionTestBean;
import com.ismailmekni.mxreflection.beans.inheritance.BeanTestChildWithoutExpression;
import com.ismailmekni.mxreflection.core.Calculator;
import com.ismailmekni.mxreflection.exceptions.NotValidExpressionException;
import com.ismailmekni.mxreflection.factory.MXFactory;
import org.junit.Test;

import static org.junit.Assert.*;


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
    public void calculateExpressionInParentClassTest(){

        BeanTestChildWithoutExpression beanTestChild = new BeanTestChildWithoutExpression();
        beanTestChild.setField1(5);
        beanTestChild.setField2(2);

        Calculator<BeanTestChildWithoutExpression> calculator = MXFactory.createCalculator(BeanTestChildWithoutExpression.class);

        calculator.calculate(beanTestChild);

        assertEquals(3, beanTestChild.getField3(), 0.001);
        assertEquals(10, beanTestChild.getField4(), 0.001);
    }

    @Test(expected = NotValidExpressionException.class)
    public void throwExpressionIsNotValidExceptionForFieldTest() {
        MXFactory.createCalculator(ChildInvalidExpressionTestBean.class);
    }
}
