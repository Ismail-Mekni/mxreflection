package com.ismail.mxreflection.core;

import com.ismail.mxreflection.beans.*;
import com.ismail.mxreflection.core.impl.MXCalculator;
import com.ismail.mxreflection.exceptions.NullFieldValueException;
import com.ismail.mxreflection.exceptions.UnparseableFieldException;
import com.ismail.mxreflection.exceptions.UnparseableResultException;
import com.ismail.mxreflection.factory.MXFactory;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MXCalculatorTest {

    @Test
    public void calculateInitializerTest() {

        MXCalculator<BeanTest> calculator = MXFactory.createCalculator(BeanTest.class);

        assertNotNull(calculator.getFieldOrder());

        assertNotNull(calculator.getFieldOrder().getOrderedFields());

        assertEquals(2, calculator.getFieldOrder().getOrderedFields().size());

        assertEquals("field3", calculator.getFieldOrder().getOrderedFields().poll().getFieldName());

        assertEquals("field4", calculator.getFieldOrder().getOrderedFields().poll().getFieldName());

    }

    @Test
    public void calculateSimpleBeanWithPublicAndDoubleFieldsTest() {
        Calculator<BeanTest> calculator = MXFactory.createCalculator(BeanTest.class);

        BeanTest beanTest = new BeanTest();

        beanTest.field1 = 5;

        beanTest.field2 = 6;

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test
    public void calculateBeanWithReadParserTest() {

        Calculator<BeanTestParser> calculator = MXFactory.createCalculator(BeanTestParser.class);

        BeanTestParser beanTest = new BeanTestParser();

        beanTest.field1 = "5";

        beanTest.field2 = 6;

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test(expected = UnparseableFieldException.class)
    public void calculateStringUnparseableFieldExceptionTest() {
        Calculator<BeanTestParser> calculator = MXFactory.createCalculator(BeanTestParser.class);

        BeanTestParser beanTest = new BeanTestParser();

        beanTest.field1 = "Hello world";

        beanTest.field2 = 6;

        calculator.calculate(beanTest);
    }

    @Test(expected = UnparseableFieldException.class)
    public void calculateThrowUparseableFieldExceptionTest() {
        Calculator<BeanTestUnparseableField> calculator = MXFactory.createCalculator(BeanTestUnparseableField.class);

        BeanTestUnparseableField beanTest = new BeanTestUnparseableField();

        beanTest.field1 = new HashMap();

        beanTest.field2 = 6;

        calculator.calculate(beanTest);
    }

    @Test
    public void calculateBeanWithWriteParserTest() {
        Calculator<BeanTestParserWrite> calculator = MXFactory.createCalculator(BeanTestParserWrite.class);

        BeanTestParserWrite beanTestParser = new BeanTestParserWrite();

        beanTestParser.field1 = "4.2";

        beanTestParser.field2 = 6;

        calculator.calculate(beanTestParser);

        assertEquals("-1.8", beanTestParser.field3);
        assertEquals(25.2, beanTestParser.field5, 0.01);
        assertEquals(-0.27941549819, beanTestParser.field6, 0.0001);
        assertEquals(Long.valueOf(-2), beanTestParser.field10);
        assertEquals(BigInteger.valueOf(-2), beanTestParser.field15);
        assertEquals(-2, beanTestParser.field9);

    }

    @Test(expected = UnparseableResultException.class)
    public void calculateThrowUnparseableResultExceptionTest() {
        Calculator<BeanTestUnparseableResult> calculator = MXFactory.createCalculator(BeanTestUnparseableResult.class);

        BeanTestUnparseableResult unparseableResult = new BeanTestUnparseableResult();


        unparseableResult.field1 = "5";

        unparseableResult.field2 = 6;

        calculator.calculate(unparseableResult);
    }

    @Test(expected = NullFieldValueException.class)
    public void calculateThrowNullPointerExceptionTest(){
        Calculator<BeanTestNullFieldValue> calculator = MXFactory.createCalculator(BeanTestNullFieldValue.class);

        BeanTestNullFieldValue bean = new BeanTestNullFieldValue();
        bean.field1= 1.2;

        calculator.calculate(bean);

    }

    @Test
    public void calculateWithPrivateArgumentsTest(){

        Calculator<BeanTestPrivateArgument> calculator = MXFactory.createCalculator(BeanTestPrivateArgument.class);

        BeanTestPrivateArgument beanTest = new BeanTestPrivateArgument(5, 6);

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test
    public void calculateWithPrivateFieldAsResultExpressionTest(){
        Calculator<BeanTestPrivateExpression> calculator = MXFactory.createCalculator(BeanTestPrivateExpression.class);

        BeanTestPrivateExpression beanTest = new BeanTestPrivateExpression(5, 6);

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.getField3(), 0.01);

        assertEquals(10.0, beanTest.getField4(), 0.01);
    }

    @Test
    public void calculateCollections(){
        Calculator<BeanTest> calculator = MXFactory.createCalculator(BeanTest.class);

        List<BeanTest> beans = Arrays.asList(new BeanTest(2, 5), new BeanTest(3, 2.5));

        calculator.calculate(beans);

        assertEquals(-3, beans.get(0).field3, 0.001);
        assertEquals(4, beans.get(0).field4, 0.001);

        assertEquals(0.5, beans.get(1).field3, 0.001);
        assertEquals(6, beans.get(1).field4, 0.001);
    }

    @Test
    public void calculateWithAnnotationMix() {
        Calculator<BeanTestAnnotationMix> calculatorMix = MXFactory.createCalculator(BeanTestAnnotationMix.class);

        BeanTestAnnotationMix beanMix = new BeanTestAnnotationMix();

        beanMix.field1="5";
        beanMix.field2="3";

        calculatorMix.calculate(beanMix);

        assertEquals("8.0", beanMix.field3);

        assertEquals("13.0", beanMix.field4);

    }

    @Test
    public void calculateOneArgNonAnnotatedField() {
        BeanTestWithFieldDependency beanTest = new BeanTestWithFieldDependency();
        beanTest.field1 = "2.2";
        beanTest.field2 = 5;

        Calculator<BeanTestWithFieldDependency> calculator = MXFactory.createCalculator(BeanTestWithFieldDependency.class);
        calculator.calculate(beanTest);

        assertEquals("-2.8", beanTest.field3);
        assertEquals(-0.736, beanTest.field4, 0.01);
        assertEquals(-2.258, beanTest.field5, 0.01);
        assertEquals(-6, beanTest.field6);
    }

}
