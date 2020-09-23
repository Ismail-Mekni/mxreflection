package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.Variable;
import com.ismail.mathreflection.exceptions.DuplicatedVariableNameException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtility {

    public static List<Field> getClassFields(Class clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static Set<String> getClassFieldNames(Class clazz) {
        Set<String> fieldNames = Arrays.stream(clazz.getDeclaredFields())
                .map(field -> field.getAnnotation(Variable.class) != null ? field.getAnnotation(Variable.class).value() : field.getName())
                .collect(Collectors.toSet());
        if (fieldNames.size() != clazz.getDeclaredFields().length)
            throw new DuplicatedVariableNameException(clazz.getName());
        else return fieldNames;
    }

    public static String getFieldName(Field field) {
        return field.getAnnotation(Variable.class) != null ? field.getAnnotation(Variable.class).value() : field.getName();
    }

    public static Object getFieldValue(String field, Object object) throws IllegalAccessException {
        Class objectClass = object.getClass();

        try {
            return object.getClass().getDeclaredField(field).get(object);
        } catch (NoSuchFieldException e) {
            return getFieldByVariable(field, objectClass).get(object);
        }
    }

    public static void setValueToField(Object object, String field, Object value) throws NoSuchFieldException, IllegalAccessException {
        object.getClass().getDeclaredField(field).set(object, value);
    }

    private static Field getFieldByVariable(String var, Class clazz) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.getAnnotation(Variable.class).value().equals(var)).findFirst().get();
    }
}
