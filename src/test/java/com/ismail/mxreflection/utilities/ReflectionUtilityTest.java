package com.ismail.mxreflection.utilities;

import com.ismail.mxreflection.beans.BeanTest;
import com.ismail.mxreflection.beans.BeanTestDuplicateName;
import com.ismail.mxreflection.beans.BeanTestParserWrite;
import com.ismail.mxreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismail.mxreflection.exceptions.DuplicatedVariableNameException;
import com.ismail.mxreflection.exceptions.FieldWithNameNotFoundException;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ReflectionUtilityTest {

    @Test
    public void getClassFieldsTest(){

        List<Field> fields = ReflectionUtility.getClassFields(BeanTest.class);

        assertEquals(5, fields.size());
        assertEquals("field1", fields.get(0).getName());
        assertEquals("field2", fields.get(1).getName());
        assertEquals("field3", fields.get(2).getName());
        assertEquals("field4", fields.get(3).getName());
        assertEquals("field5", fields.get(4).getName());
    }

    @Test
    public void getClassFieldNamesTest(){

        Set<String> fieldNames = ReflectionUtility.getClassFieldNames(BeanTest.class);

        assertEquals(5, fieldNames.size());
        assertTrue(fieldNames.contains("f1"));
        assertTrue(fieldNames.contains("f2"));
        assertTrue(fieldNames.contains("field3"));
        assertTrue(fieldNames.contains("field4"));
        assertTrue(fieldNames.contains("field5"));
    }

    @Test(expected = DuplicatedVariableNameException.class)
    public void getClassFieldNamesDuplicatedVariableNameExceptionTest(){

        ReflectionUtility.getClassFieldNames(BeanTestDuplicateName.class);
    }

    @Test
    public void getFieldNameWithoutVariableAnnotationTest() throws NoSuchFieldException {

        String fieldName = ReflectionUtility.getVariableName(BeanTest.class.getDeclaredField("field5"));
        assertEquals("field5", fieldName);

    }

    @Test
    public void getFieldNameWithVariableAnnotationFieldTest() throws NoSuchFieldException {

        String annotatedFieldName = ReflectionUtility.getVariableName(BeanTest.class.getField("field1"));
        assertEquals("f1", annotatedFieldName);
    }

    @Test
    public void getFieldValueWithoutVariableAnnotationTest() throws IllegalAccessException {
        BeanTest beanTest = new BeanTest();
        beanTest.setField5("5.258");
        beanTest.setField4(3.5);
        String field5Value = (String) ReflectionUtility.getFieldValue("field5", beanTest);
        double field4Value = (double) ReflectionUtility.getFieldValue("field4", beanTest);

        assertEquals("5.258", field5Value);
        assertEquals(3.5, field4Value, 0.00001);

    }

    @Test
    public void getFieldValueWithVariableAnnotationTest() throws IllegalAccessException {
        BeanTestParserWrite beanTest = new BeanTestParserWrite();
        beanTest.field1 = "5.258";
        beanTest.field2 = 3;
        String field5Value = (String) ReflectionUtility.getFieldValue("f1", beanTest);
        int field4Value = (int) ReflectionUtility.getFieldValue("f2", beanTest);

        assertEquals("5.258", field5Value);
        assertEquals(3, field4Value);

    }

    @Test(expected = FieldWithNameNotFoundException.class)
    public void getFieldValueTestNoSuchFieldException() throws IllegalAccessException {
        BeanTestParserWrite beanTest = new BeanTestParserWrite();

        ReflectionUtility.getFieldValue("hello", beanTest);
    }

    @Test
    public void setValueToPublicFieldTest(){

        BeanTest beanTest = new BeanTest();

        ReflectionUtility.setValueToField(beanTest, "field1", 3.5);

        assertEquals(3.5, beanTest.field1, 0.0001);
    }

    @Test
    public void setValueToPrivateFieldTest(){
        BeanTest beanTest = new BeanTest();

        ReflectionUtility.setValueToField(beanTest, "field5", "3.5");

        assertEquals("3.5", beanTest.getField5());
    }

    @Test(expected = AccessNotAllowedToWriteValueException.class)
    public void setValueToFieldUnknownFieldNameTest(){
        BeanTest beanTest = new BeanTest();

        ReflectionUtility.setValueToField(beanTest, "hello", "3.0");
    }
}
