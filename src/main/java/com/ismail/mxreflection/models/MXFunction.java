package com.ismail.mxreflection.models;

import com.ismail.mxreflection.annotations.Expression;
import com.ismail.mxreflection.exceptions.NotValidExpressionException;
import com.ismail.mxreflection.utilities.ReflectionUtility;
import org.mariuszgromada.math.mxparser.Function;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MXFunction extends AbstractFunction<Function> {

    private static final String FUNCTION_SIGNATURE_PREFIX = "f(";

    private static final String FUNCTION_SIGNATURE_POSTFIX = ") =";

    private static final String EXPRESSION_ARGUMENTS_POSTFIX = ")";

    private static final String FUNCTION_ARGUMENTS_DELIMITER = ", ";

    public MXFunction(Field field, Class clazz) {
        this.fieldName = ReflectionUtility.getFieldName(field);

        this.argumentName = ReflectionUtility.getArgumentName(field);
        String expression = field.getAnnotation(Expression.class).value();
        this.arguments = extractArguments(expression, clazz);
        this.function = generateFunction(arguments, field);
        generatePredicate();
    }

    @Override
    protected Function generateFunction(Set<String> arguments, Field field) {

        String expression = field.getAnnotation(Expression.class).value();

        String funExp = FUNCTION_SIGNATURE_PREFIX + String.join(FUNCTION_ARGUMENTS_DELIMITER, arguments) + FUNCTION_SIGNATURE_POSTFIX + expression;
        Function function = new Function(funExp);
        if (!function.checkSyntax()) {
            throw new NotValidExpressionException(funExp, field.getName(), function.getErrorMessage());
        }

        return function;
    }

    @Override
    protected void generatePredicate() {
        this.lambda = (List<Double> arguments) -> {

            String argsExpression = FUNCTION_SIGNATURE_PREFIX
                    + String.join(FUNCTION_ARGUMENTS_DELIMITER, arguments.stream().map(var -> var.toString()).collect(Collectors.toSet()))
                    + EXPRESSION_ARGUMENTS_POSTFIX;
            org.mariuszgromada.math.mxparser.Expression expression = new org.mariuszgromada.math.mxparser.Expression(argsExpression, this.function);

            return expression.calculate();
        };
    }
}
