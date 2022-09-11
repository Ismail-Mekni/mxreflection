package com.ismail.mxreflection.core;

import com.ismail.mxreflection.beans.BeanTest;
import com.ismail.mxreflection.beans.BeanTestDuplicateName;
import com.ismail.mxreflection.beans.BeanTestParserWrite;
import com.ismail.mxreflection.core.ReflectionBean;
import com.ismail.mxreflection.exceptions.DuplicatedArgumentNameException;
import com.ismail.mxreflection.exceptions.FieldWithNameNotFoundException;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReflectionBeanTest {

    @Test
    public void getClassFieldsTest(){

        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        List<Field> fields = reflectionBean.getFields();

        assertEquals(5, fields.size());
        assertEquals("field1", fields.get(0).getName());
        assertEquals("field2", fields.get(1).getName());
        assertEquals("field3", fields.get(2).getName());
        assertEquals("field4", fields.get(3).getName());
        assertEquals("field5", fields.get(4).getName());
    }

    @Test
    public void getClassFieldNamesTest(){

        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        Set<String> fieldNames = reflectionBean.getClassFieldNames();

        assertEquals(5, fieldNames.size());
        assertTrue(fieldNames.contains("f1"));
        assertTrue(fieldNames.contains("f2"));
        assertTrue(fieldNames.contains("field3"));
        assertTrue(fieldNames.contains("field4"));
        assertTrue(fieldNames.contains("field5"));
    }

    @Test(expected = DuplicatedArgumentNameException.class)
    public void getClassFieldNamesDuplicatedArgumentNameExceptionTest(){

        ReflectionBean reflectionBean = new ReflectionBean(BeanTestDuplicateName.class);
        reflectionBean.getClassFieldNames();

    }

    @Test
    public void getFieldNameWithoutArgAnnotationTest() throws NoSuchFieldException {

        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        String fieldName = reflectionBean.getArgumentName(BeanTest.class.getDeclaredField("field5"));
        assertEquals("field5", fieldName);

    }

    @Test
    public void getFieldNameWithArgAnnotationFieldTest() throws NoSuchFieldException {
        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        String annotatedFieldName = reflectionBean.getArgumentName(BeanTest.class.getField("field1"));
        assertEquals("f1", annotatedFieldName);
    }

    @Test
    public void getFieldValueWithoutArgAnnotationTest() throws IllegalAccessException {
        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        BeanTest beanTest = new BeanTest();
        beanTest.setField5("5.258");
        beanTest.setField4(3.5);
        String field5Value = (String) reflectionBean.getFieldValue("field5", beanTest);
        double field4Value = (double) reflectionBean.getFieldValue("field4", beanTest);

        assertEquals("5.258", field5Value);
        assertEquals(3.5, field4Value, 0.00001);

    }

    @Test
    public void getFieldValueWithArgAnnotationTest() throws IllegalAccessException {
        ReflectionBean reflectionBean = new ReflectionBean(BeanTestParserWrite.class);

        BeanTestParserWrite beanTest = new BeanTestParserWrite();
        beanTest.field1 = "5.258";
        beanTest.field2 = 3;
        String field5Value = (String) reflectionBean.getFieldValue("f1", beanTest);
        int field4Value = (int) reflectionBean.getFieldValue("f2", beanTest);

        assertEquals("5.258", field5Value);
        assertEquals(3, field4Value);

    }

    @Test(expected = FieldWithNameNotFoundException.class)
    public void getFieldValueTestNoSuchFieldException() throws IllegalAccessException {
        ReflectionBean reflectionBean = new ReflectionBean(BeanTestParserWrite.class);

        BeanTestParserWrite beanTest = new BeanTestParserWrite();

        reflectionBean.getFieldValue("hello", beanTest);
    }

    @Test
    public void setValueToPublicFieldTest(){

        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        BeanTest beanTest = new BeanTest();

        reflectionBean.setValueToField(beanTest, "field1", 3.5);

        assertEquals(3.5, beanTest.field1, 0.0001);
    }

    @Test
    public void setValueToPrivateFieldTest(){
        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        BeanTest beanTest = new BeanTest();

        reflectionBean.setValueToField(beanTest, "field5", "3.5");

        assertEquals("3.5", beanTest.getField5());
    }

    @Test(expected = FieldWithNameNotFoundException.class)
    public void setValueToFieldUnknownFieldNameTest(){
        ReflectionBean reflectionBean = new ReflectionBean(BeanTest.class);

        BeanTest beanTest = new BeanTest();

        reflectionBean.setValueToField(beanTest, "hello", "3.0");
    }
}
