package com.ismail.mathreflection.core;

import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.exceptions.NullFieldValueException;
import com.ismail.mathreflection.exceptions.UnparseableFieldException;
import com.ismail.mathreflection.exceptions.UnparseableResultException;
import com.ismail.mathreflection.factory.MXFactory;
import com.ismail.mathreflection.utilities.*;
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
    public void calculateWithPrivateVariablesTest(){

        Calculator<BeanTestPrivateVariable> calculator = MXFactory.createCalculator(BeanTestPrivateVariable.class);

        BeanTestPrivateVariable beanTest = new BeanTestPrivateVariable(5, 6);

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test
    public void calculateWithPrivateFieldAsResultFormulaTest(){
        Calculator<BeanTestPrivateFormula> calculator = MXFactory.createCalculator(BeanTestPrivateFormula.class);

        BeanTestPrivateFormula beanTest = new BeanTestPrivateFormula(5, 6);

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

}
