package com.ismail.mxreflection.utilities;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionUtility {

    private ReflectionUtility() {
    }

    public static List<Field> getClassFields(Class clazz) {

        List<Field> classFields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));

        if(clazz.getSuperclass() == null) {
            return classFields;
        }

        classFields.addAll(getClassFields(clazz.getSuperclass()));

        return classFields;
    }

    public static Object getValueFromField(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }
}
