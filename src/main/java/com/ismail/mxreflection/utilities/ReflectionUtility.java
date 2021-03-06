package com.ismail.mxreflection.utilities;

import com.ismail.mxreflection.annotations.Arg;
import com.ismail.mxreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismail.mxreflection.exceptions.DuplicatedArgumentNameException;
import com.ismail.mxreflection.exceptions.FieldWithNameNotFoundException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtility {

    private ReflectionUtility() {
    }

    public static List<Field> getClassFields(Class clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static Set<String> getClassFieldNames(Class clazz) {
        Set<String> fieldNames = Arrays.stream(clazz.getDeclaredFields())
                .map(field -> field.getAnnotation(Arg.class) != null ? field.getAnnotation(Arg.class).value() : field.getName())
                .collect(Collectors.toSet());
        if (fieldNames.size() != clazz.getDeclaredFields().length)
            throw new DuplicatedArgumentNameException(clazz.getName());

        return fieldNames;
    }

    public static String getArgumentName(Field field) {
        return field.getAnnotation(Arg.class) != null ? field.getAnnotation(Arg.class).value() : field.getName();
    }

    public static String getFieldName(Field field) {
        return field.getName();
    }

    public static Object getFieldValue(String fieldName, Object object) throws IllegalAccessException {
        Class objectClass = object.getClass();

        try {
            return getValueFromField(object.getClass().getDeclaredField(fieldName), object);
        } catch (NoSuchFieldException e) {
            return getValueFromField(getFieldByArgument(fieldName, objectClass), object);
        }
    }

    public static void setValueToField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new AccessNotAllowedToWriteValueException(fieldName);
        }
    }

    private static Field getFieldByArgument(String var, Class clazz) {

        try {

            return Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Arg.class) && f.getAnnotation(Arg.class).value().equals(var)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new FieldWithNameNotFoundException(var);
        }
    }

    private static Object getValueFromField(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }
}
