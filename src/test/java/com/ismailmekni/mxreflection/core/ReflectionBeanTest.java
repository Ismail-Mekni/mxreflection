package com.ismailmekni.mxreflection.core;

import com.ismailmekni.mxreflection.beans.TestBean;
import com.ismailmekni.mxreflection.beans.DuplicateNameTestBean;
import com.ismailmekni.mxreflection.beans.ParserWriteTestBean;
import com.ismailmekni.mxreflection.exceptions.DuplicatedArgumentNameOrInnerClassDetectedException;
import com.ismailmekni.mxreflection.exceptions.FieldWithNameNotFoundException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionBeanTest {

    @Test
    public void getClassFieldsTest() {

        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        List<Field> fields = reflectionBean.getFields();

        assertEquals(5, fields.size());
        assertEquals("field1", fields.get(0).getName());
        assertEquals("field2", fields.get(1).getName());
        assertEquals("field3", fields.get(2).getName());
        assertEquals("field4", fields.get(3).getName());
        assertEquals("field5", fields.get(4).getName());
    }

    @Test
    public void getClassFieldNamesTest() {

        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        Set<String> fieldNames = reflectionBean.getClassFieldNames();

        assertEquals(5, fieldNames.size());
        assertTrue(fieldNames.contains("f1"));
        assertTrue(fieldNames.contains("f2"));
        assertTrue(fieldNames.contains("field3"));
        assertTrue(fieldNames.contains("field4"));
        assertTrue(fieldNames.contains("field5"));
    }

    @Test
    public void getClassFieldNamesDuplicatedArgumentNameExceptionTest() {

        assertThrows(DuplicatedArgumentNameOrInnerClassDetectedException.class, () -> {

            ReflectionBean reflectionBean = new ReflectionBean(DuplicateNameTestBean.class);
            reflectionBean.getClassFieldNames();
        });

    }

    @Test
    public void getFieldNameWithoutArgAnnotationTest() throws NoSuchFieldException {

        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        String fieldName = reflectionBean.getArgumentName(TestBean.class.getDeclaredField("field5"));
        assertEquals("field5", fieldName);

    }

    @Test
    public void getFieldNameWithArgAnnotationFieldTest() throws NoSuchFieldException {
        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        String annotatedFieldName = reflectionBean.getArgumentName(TestBean.class.getField("field1"));
        assertEquals("f1", annotatedFieldName);
    }

    @Test
    public void getFieldValueWithoutArgAnnotationTest() throws IllegalAccessException {
        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        TestBean testBean = new TestBean();
        testBean.setField5("5.258");
        testBean.setField4(3.5);
        String field5Value = (String) reflectionBean.getFieldValue("field5", testBean);
        double field4Value = (double) reflectionBean.getFieldValue("field4", testBean);

        assertEquals("5.258", field5Value);
        assertEquals(3.5, field4Value, 0.00001);

    }

    @Test
    public void getFieldValueWithArgAnnotationTest() throws IllegalAccessException {
        ReflectionBean reflectionBean = new ReflectionBean(ParserWriteTestBean.class);

        ParserWriteTestBean beanTest = new ParserWriteTestBean();
        beanTest.field1 = "5.258";
        beanTest.field2 = 3;
        String field5Value = (String) reflectionBean.getFieldValue("f1", beanTest);
        int field4Value = (int) reflectionBean.getFieldValue("f2", beanTest);

        assertEquals("5.258", field5Value);
        assertEquals(3, field4Value);

    }

    @Test
    public void getFieldValueTestNoSuchFieldException() {
        ReflectionBean reflectionBean = new ReflectionBean(ParserWriteTestBean.class);

        ParserWriteTestBean beanTest = new ParserWriteTestBean();

        assertThrows(FieldWithNameNotFoundException.class, ()-> {
            reflectionBean.getFieldValue("hello", beanTest);
        });
    }

    @Test
    public void setValueToPublicFieldTest() {

        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        TestBean testBean = new TestBean();

        reflectionBean.setValueToField(testBean, "field1", 3.5);

        assertEquals(3.5, testBean.field1, 0.0001);
    }

    @Test
    public void setValueToPrivateFieldTest() {
        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        TestBean testBean = new TestBean();

        reflectionBean.setValueToField(testBean, "field5", "3.5");

        assertEquals("3.5", testBean.getField5());
    }

    @Test
    public void setValueToFieldUnknownFieldNameTest() {
        ReflectionBean reflectionBean = new ReflectionBean(TestBean.class);

        TestBean testBean = new TestBean();

        assertThrows(FieldWithNameNotFoundException.class, ()-> {
            reflectionBean.setValueToField(testBean, "hello", "3.0");
        });
    }
}
