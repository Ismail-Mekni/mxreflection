package com.ismailmekni.mxreflection.core;

import com.ismailmekni.mxreflection.beans.*;
import com.ismailmekni.mxreflection.core.impl.MXCalculator;
import com.ismailmekni.mxreflection.exceptions.NullFieldValueException;
import com.ismailmekni.mxreflection.exceptions.UnparseableFieldException;
import com.ismailmekni.mxreflection.exceptions.UnparseableResultException;
import com.ismailmekni.mxreflection.factory.MXFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MXCalculatorTest {

    @Test
    public void calculateInitializerTest() {

        MXCalculator<TestBean> calculator = MXFactory.createCalculator(TestBean.class);

        assertNotNull(calculator.getFieldOrder());

        assertNotNull(calculator.getFieldOrder().getOrderedFields());

        Assertions.assertEquals(2, calculator.getFieldOrder().getOrderedFields().size());

        assertEquals("field3", calculator.getFieldOrder().getOrderedFields().poll().getFieldName());

        assertEquals("field4", calculator.getFieldOrder().getOrderedFields().poll().getFieldName());

    }

    @Test
    public void calculateSimpleBeanWithPublicAndDoubleFieldsTest() {
        Calculator<TestBean> calculator = MXFactory.createCalculator(TestBean.class);

        TestBean testBean = new TestBean();

        testBean.field1 = 5;

        testBean.field2 = 6;

        calculator.calculate(testBean);

        assertEquals(-1.0, testBean.field3, 0.01);

        assertEquals(10.0, testBean.field4, 0.01);
    }

    @Test
    public void calculateBeanWithReadParserTest() {

        Calculator<ParserTestBean> calculator = MXFactory.createCalculator(ParserTestBean.class);

        ParserTestBean beanTest = new ParserTestBean();

        beanTest.field1 = "5";

        beanTest.field2 = 6;

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test
    public void calculateStringUnparseableFieldExceptionTest() {
        Calculator<ParserTestBean> calculator = MXFactory.createCalculator(ParserTestBean.class);

        ParserTestBean beanTest = new ParserTestBean();

        beanTest.field1 = "Hello world";

        beanTest.field2 = 6;

        assertThrows(UnparseableFieldException.class, () -> {
            calculator.calculate(beanTest);
        });
    }

    @Test
    public void calculateThrowUparseableFieldExceptionTest() {
        Calculator<UnparseableFieldTestBean> calculator = MXFactory.createCalculator(UnparseableFieldTestBean.class);

        UnparseableFieldTestBean beanTest = new UnparseableFieldTestBean();

        beanTest.field1 = new HashMap();

        beanTest.field2 = 6;

        assertThrows(UnparseableFieldException.class, () -> {
            calculator.calculate(beanTest);
        });
    }

    @Test
    public void calculateBeanWithWriteParserTest() {
        Calculator<ParserWriteTestBean> calculator = MXFactory.createCalculator(ParserWriteTestBean.class);

        ParserWriteTestBean beanTestParser = new ParserWriteTestBean();

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

    @Test
    public void calculateThrowUnparseableResultExceptionTest() {
        Calculator<UnparseableResultTestBean> calculator = MXFactory.createCalculator(UnparseableResultTestBean.class);

        UnparseableResultTestBean unparseableResult = new UnparseableResultTestBean();


        unparseableResult.field1 = "5";

        unparseableResult.field2 = 6;

        assertThrows(UnparseableResultException.class, () -> {
            calculator.calculate(unparseableResult);
        });
    }

    @Test
    public void calculateThrowNullPointerExceptionTest() {
        Calculator<NullFieldValueTestBean> calculator = MXFactory.createCalculator(NullFieldValueTestBean.class);

        NullFieldValueTestBean bean = new NullFieldValueTestBean();
        bean.field1 = 1.2;

        assertThrows(NullFieldValueException.class, () -> {
            calculator.calculate(bean);
        });
    }

    @Test
    public void calculateWithPrivateArgumentsTest() {

        Calculator<PrivateArgumentTestBean> calculator = MXFactory.createCalculator(PrivateArgumentTestBean.class);

        PrivateArgumentTestBean beanTest = new PrivateArgumentTestBean(5, 6);

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test
    public void calculateWithPrivateFieldAsResultExpressionTest() {
        Calculator<PrivateExpressionTestBean> calculator = MXFactory.createCalculator(PrivateExpressionTestBean.class);

        PrivateExpressionTestBean beanTest = new PrivateExpressionTestBean(5, 6);

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.getField3(), 0.01);

        assertEquals(10.0, beanTest.getField4(), 0.01);
    }

    @Test
    public void calculateCollections() {
        Calculator<TestBean> calculator = MXFactory.createCalculator(TestBean.class);

        List<TestBean> beans = Arrays.asList(new TestBean(2, 5), new TestBean(3, 2.5));

        calculator.calculate(beans);

        assertEquals(-3, beans.get(0).field3, 0.001);
        assertEquals(4, beans.get(0).field4, 0.001);

        assertEquals(0.5, beans.get(1).field3, 0.001);
        assertEquals(6, beans.get(1).field4, 0.001);
    }

    @Test
    public void calculateWithAnnotationMix() {
        Calculator<AnnotationMixTestBean> calculatorMix = MXFactory.createCalculator(AnnotationMixTestBean.class);

        AnnotationMixTestBean beanMix = new AnnotationMixTestBean();

        beanMix.field1 = "5";
        beanMix.field2 = "3";

        calculatorMix.calculate(beanMix);

        assertEquals("8.0", beanMix.field3);

        assertEquals("13.0", beanMix.field4);

    }

    @Test
    public void calculateOneArgNonAnnotatedField() {
        WithFieldDependencyTestBean beanTest = new WithFieldDependencyTestBean();
        beanTest.field1 = "2.2";
        beanTest.field2 = 5;

        Calculator<WithFieldDependencyTestBean> calculator = MXFactory.createCalculator(WithFieldDependencyTestBean.class);
        calculator.calculate(beanTest);

        assertEquals("-2.8", beanTest.field3);
        assertEquals(-0.736, beanTest.field4, 0.01);
        assertEquals(-1.697, beanTest.field5, 0.01);
        assertEquals(-6, beanTest.field6);
    }

}
