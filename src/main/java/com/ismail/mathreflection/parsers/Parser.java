package com.ismail.mathreflection.parsers;

import com.ismail.mathreflection.exceptions.UnparseableFieldException;
import com.ismail.mathreflection.exceptions.UnparseableResultException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {

    private static Map<Class, Method> parsingMethodMap;

    static {
        parsingMethodMap = new HashMap<>();
        try {

            parsingMethodMap.put(Double.class, Double.class.getDeclaredMethod("valueOf", double.class));
            parsingMethodMap.put(Integer.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(Short.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(Float.class, Float.class.getDeclaredMethod("valueOf", double.class));
            parsingMethodMap.put(Long.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(String.class, String.class.getDeclaredMethod("valueOf", double.class));
            parsingMethodMap.put(BigInteger.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(BigDecimal.class, BigDecimal.class.getDeclaredMethod("valueOf", double.class));
            parsingMethodMap.put(Byte.class, Byte.class.getDeclaredMethod("valueOf", byte.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public static Set<Double> parseVariables(Set<Object> vars) {
        return vars.stream().map(var -> {
            try {
                return Double.parseDouble(String.valueOf(var));
            } catch (Exception exception) {
                throw new UnparseableFieldException(var.getClass().getName());
            }
        }).collect(Collectors.toSet());
    }

    public static <T> T parseResult(double result, Class<T> type) {
        try {
            if (type.isPrimitive())
                return (T) parseToPrimitiveType(result, (Class<? extends Number>) type);
            else
                return (T) parseToType(result, (Class<? extends Number>) type);

        } catch (Exception e) {
            throw new UnparseableResultException(type.getName());
        }
    }

    private static <T extends Number> T parseToPrimitiveType(Double result, Class<T> type) throws InvocationTargetException, IllegalAccessException {

        Method methodToCall = Arrays.stream(Number.class.getDeclaredMethods()).filter(method -> method.getReturnType().equals(type)).findFirst().get();


        return (T) methodToCall.invoke(result);
    }

    private static <T extends Number> T parseToType(double result, Class<T> type) throws InvocationTargetException, IllegalAccessException {
        return (T) parsingMethodMap.get(type).invoke(null, result);
    }
}
