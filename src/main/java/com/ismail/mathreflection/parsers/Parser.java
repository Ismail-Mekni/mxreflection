package com.ismail.mathreflection.parsers;

import com.ismail.mathreflection.exceptions.UnparseableFieldException;

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
}
