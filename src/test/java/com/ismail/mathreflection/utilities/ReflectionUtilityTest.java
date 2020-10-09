package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.beans.BeanTest;
import com.ismail.mathreflection.beans.BeanTestDuplicateName;
import com.ismail.mathreflection.exceptions.DuplicatedVariableNameException;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Ref;
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

        String fieldName = ReflectionUtility.getFieldName(BeanTest.class.getDeclaredField("field5"));
        assertEquals("field5", fieldName);

    }

    @Test
    public void getFieldNameWithVariableAnnotationFieldTest() throws NoSuchFieldException {

        String annotatedFieldName = ReflectionUtility.getFieldName(BeanTest.class.getField("field1"));
        assertEquals("f1", annotatedFieldName);
    }

    @Test
    public void getFieldValueTest(){

    }

    @Test(expected = NoSuchFieldException.class)
    public void getFieldValueTestNoSuchFieldException(){

    }

    @Test
    public void setValueToFieldTest(){

    }
}
