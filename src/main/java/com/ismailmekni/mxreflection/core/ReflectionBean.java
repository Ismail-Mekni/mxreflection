package com.ismailmekni.mxreflection.core;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.exceptions.AccessNotAllowedToWriteValueException;
import com.ismailmekni.mxreflection.exceptions.DuplicatedArgumentNameException;
import com.ismailmekni.mxreflection.exceptions.FieldWithNameNotFoundException;
import com.ismailmekni.mxreflection.utilities.ReflectionUtility;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectionBean {

    private Class clazz;

    private List<Field> fields;

    public ReflectionBean(Class clazz) {
        this.clazz = clazz;
        this.fields = ReflectionUtility.getClassFields(clazz);
    }

    public Set<String> getClassFieldNames() {
        Set<String> fieldNames = this.fields.stream()
                .map(field -> field.getAnnotation(Arg.class) != null ? field.getAnnotation(Arg.class).value() : field.getName())
                .collect(Collectors.toSet());

        if (fieldNames.size() != this.fields.size())
            throw new DuplicatedArgumentNameException(this.clazz.getName());

        return fieldNames;
    }

    public String getArgumentName(Field field) {
        return field.getAnnotation(Arg.class) != null ? field.getAnnotation(Arg.class).value() : field.getName();
    }

    public String getFieldName(Field field) {
        return field.getName();
    }

    public Object getFieldValue(String fieldName, Object object) throws IllegalAccessException {

        try {
            return ReflectionUtility.getValueFromField(this.getFieldByName(fieldName), object);
        } catch (FieldWithNameNotFoundException e) {
            return ReflectionUtility.getValueFromField(getFieldByArgument(fieldName), object);
        }
    }

    public void setValueToField(Object object, String fieldName, Object value) {
        try {

            Field field = this.getFieldByName(fieldName);
            field.setAccessible(true);
            field.set(object, value);

        } catch (IllegalAccessException e) {
            throw new AccessNotAllowedToWriteValueException(fieldName);
        }
    }

    private Field getFieldByArgument(String arg) {

        try {
            Optional<Field> fieldWithArgument= this.fields.stream()
                    .filter(f -> f.isAnnotationPresent(Arg.class) && f.getAnnotation(Arg.class).value().equals(arg))
                    .findFirst();
            if(!fieldWithArgument.isPresent()) {
                throw new FieldWithNameNotFoundException(arg);
            }
            return fieldWithArgument.get();
        } catch (NoSuchElementException e) {
            throw new FieldWithNameNotFoundException(arg);
        }
    }

    private Field getFieldByName(String fieldName) {

        Optional<Field> optionalField = this.fields.stream()
                .filter(f -> f.getName().equals(fieldName)).findFirst();

        if (optionalField.isPresent()) {
            return optionalField.get();
        }

        throw new FieldWithNameNotFoundException(fieldName);
    }

    public Class getFieldTypeByName(String fieldName) {
        return getFieldByName(fieldName).getType();
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
