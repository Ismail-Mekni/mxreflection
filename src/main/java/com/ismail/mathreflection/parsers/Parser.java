package com.ismail.mathreflection.parsers;

import com.ismail.mathreflection.exceptions.UnparseableFieldException;
import com.ismail.mathreflection.exceptions.UnparseableResultException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {

    public static Set<Double> parseVariables(Set<Object> vars) {
        return vars.stream().map(var -> {
            try {
                return Double.parseDouble(String.valueOf(var));
            } catch (Exception exception) {
                throw new UnparseableFieldException(var.getClass().getName());
            }
        }).collect(Collectors.toSet());
    }

    public static <T> T parseResult(Double result, Class<T> type) {
        try {
            if (type.equals(String.class))
                return (T) String.valueOf(result);
            else if (type.equals(Double.class))
                return (T) result;
            else return (T) parseNumber(result, (Class<? extends Number>) type);

        } catch (Exception e) {
            throw new UnparseableResultException(type.getName());
        }
    }

    private static <T extends Number> T parseNumber(Double result, Class<T> type) throws InvocationTargetException, IllegalAccessException {
        Method methodToCall = Arrays.stream(Number.class.getDeclaredMethods()).filter(method -> method.getReturnType().equals(type)).findFirst().get();

        return (T) methodToCall.invoke(result);
    }
}
