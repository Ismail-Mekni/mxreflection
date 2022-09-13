package com.ismailmekni.mxreflection.parsers;

import com.ismailmekni.mxreflection.exceptions.UnparseableFieldException;
import com.ismailmekni.mxreflection.exceptions.UnparseableResultException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private Parser() {
    }

    private static Map<Class, Method> parsingMethodMap;

    static {
        parsingMethodMap = new HashMap<>();
        final String objectValueOfMethodName = "valueOf";
        try {

            parsingMethodMap.put(Double.class, Double.class.getDeclaredMethod(objectValueOfMethodName, double.class));
            parsingMethodMap.put(String.class, String.class.getDeclaredMethod(objectValueOfMethodName, double.class));
            parsingMethodMap.put(double.class, Double.class.getDeclaredMethod(objectValueOfMethodName, double.class));
            parsingMethodMap.put(BigInteger.class, Parser.class.getDeclaredMethod("toBigInteger", double.class));
            parsingMethodMap.put(Long.class, Math.class.getDeclaredMethod("round", double.class));
            parsingMethodMap.put(long.class, Math.class.getDeclaredMethod("round", double.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public static List<Double> parseArguments(List<Object> args) {
        return args.stream().map(arg -> {
            try {
                return Double.parseDouble(String.valueOf(arg));
            } catch (Exception exception) {
                throw new UnparseableFieldException(arg.getClass().getName());
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
