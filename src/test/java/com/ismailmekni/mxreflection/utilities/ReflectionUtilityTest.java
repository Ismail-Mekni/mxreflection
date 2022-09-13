package com.ismailmekni.mxreflection.utilities;

import com.ismailmekni.mxreflection.beans.TestBean;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class ReflectionUtilityTest {

    @Test
    public void getClassFieldsTest(){

        List<Field> fields = ReflectionUtility.getClassFields(TestBean.class);

        assertEquals(5, fields.size());
        assertEquals("field1", fields.get(0).getName());
        assertEquals("field2", fields.get(1).getName());
        assertEquals("field3", fields.get(2).getName());
        assertEquals("field4", fields.get(3).getName());
        assertEquals("field5", fields.get(4).getName());
    }

}
