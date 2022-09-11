package com.ismail.mxreflection.inheritance;

import com.ismail.mxreflection.beans.BeanTestInvalidExpression;
import com.ismail.mxreflection.beans.inheritance.BeanTestChild;
import com.ismail.mxreflection.beans.inheritance.BeanTestChildInvalidExpression;
import com.ismail.mxreflection.beans.inheritance.BeanTestChildWithoutExpression;
import com.ismail.mxreflection.core.Calculator;
import com.ismail.mxreflection.exceptions.NotValidExpressionException;
import com.ismail.mxreflection.factory.MXFactory;
import org.junit.Test;

import static org.junit.Assert.*;


public class MxCalculatorWithInheritanceTest {

    @Test
    public void calculateExpressionWhenContainsInheritedArgumentTest() {
        BeanTestChild beanTestChild = new BeanTestChild();
        beanTestChild.setField1(5);
        beanTestChild.setField2(2);

        Calculator<BeanTestChild> calculator = MXFactory.createCalculator(BeanTestChild.class);

        calculator.calculate(beanTestChild);

        assertEquals(3, beanTestChild.getField3(), 0.001);
        assertEquals(10, beanTestChild.getField4(), 0.001);
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
        MXFactory.createCalculator(BeanTestChildInvalidExpression.class);
    }
}
