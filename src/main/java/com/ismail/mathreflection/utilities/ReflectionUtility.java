package com.ismail.mathreflection.utilities;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtility {

    public static List<Field> getClassFields(Class clazz){
        return Arrays.asList(clazz.getDeclaredFields());
    }
}
