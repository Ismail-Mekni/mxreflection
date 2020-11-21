package com.ismail.mxreflection.parsers;

import com.ismail.mxreflection.exceptions.UnparseableFieldException;
import com.ismail.mxreflection.exceptions.UnparseableResultException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private static Map<Class, Method> parsingMethodMap;

    static {
        parsingMethodMap = new HashMap<>();
        try {

            parsingMethodMap.put(Double.class, Double.class.getDeclaredMethod("valueOf", double.class));
            parsingMethodMap.put(Long.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(String.class, String.class.getDeclaredMethod("valueOf", double.class));
            parsingMethodMap.put(BigInteger.class, Parser.class.getDeclaredMethod("toBigInteger", double.class));
            parsingMethodMap.put(long.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(double.class, Double.class.getDeclaredMethod("valueOf", double.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public static List<Double> parseVariables(List<Object> vars) {
        return vars.stream().map(var -> {
            try {
                return Double.parseDouble(String.valueOf(var));
            } catch (Exception exception) {
                throw new UnparseableFieldException(var.getClass().getName());
            }
        }).collect(Collectors.toList());
    }

    public static <T> T parseResult(double result, Class<T> type) {
        try {
                return (T) parseToType(result, (Class<? extends Number>) type);

        } catch (Exception e) {
            throw new UnparseableResultException(type.getName());
        }
    }

    private static <T> T parseToType(double result, Class<T> type) throws InvocationTargetException, IllegalAccessException {
        return (T) parsingMethodMap.get(type).invoke(null, result);
    }

    private static BigInteger toBigInteger(double value) {
        return BigInteger.valueOf(Math.round(value));
    }
}
