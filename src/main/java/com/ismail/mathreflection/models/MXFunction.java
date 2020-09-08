package com.ismail.mathreflection.models;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.exceptions.FormulaIsNotValidException;
import com.ismail.mathreflection.utilities.ReflectionUtility;
import org.mariuszgromada.math.mxparser.Function;

import java.lang.reflect.Field;
import java.util.Set;

public class MXFunction extends Formula<Function> {

    private static final String FUNCTION_SIGNATURE_PREFIX = "f(";

    private static final String FUNCTION_SIGNATURE_POSTFIX = ") =";

    private static final String FUNCTION_VARIABLES_DELIMITER = ", ";

    private Function function;

    public MXFunction() {
    }

    public MXFunction(Field field, Class clazz) {
        this.fieldName = ReflectionUtility.getFieldName(field);

        String expression = field.getAnnotation(MXFormula.class).value();
        this.variables = extractVariables(expression, clazz);
        this.function = generateFunction(variables, field);
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    protected Function generateFunction(Set<String> variables, Field field) {

        String expression = field.getAnnotation(MXFormula.class).value();

        String funExp = FUNCTION_SIGNATURE_PREFIX + String.join(FUNCTION_VARIABLES_DELIMITER, variables) + FUNCTION_SIGNATURE_POSTFIX + expression;
        Function function = new Function(funExp);
        if (!function.checkSyntax()) {
            throw new FormulaIsNotValidException(funExp, field.getName(), function.getErrorMessage());
        }

        return function;
    }
}
