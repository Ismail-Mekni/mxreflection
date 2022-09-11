package com.ismail.mxreflection.utilities;

import com.ismail.mxreflection.beans.BeanTest;
import com.ismail.mxreflection.beans.BeanTestDuplicateName;
import com.ismail.mxreflection.beans.BeanTestParserWrite;
import com.ismail.mxreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismail.mxreflection.exceptions.DuplicatedArgumentNameException;
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

}
