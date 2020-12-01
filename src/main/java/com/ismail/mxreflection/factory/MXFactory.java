package com.ismail.mxreflection.factory;

import com.ismail.mxreflection.annotations.Expression;
import com.ismail.mxreflection.core.impl.MXCalculator;
import com.ismail.mxreflection.exceptions.NoExpressionFoundForTypeException;
import com.ismail.mxreflection.models.FieldOrder;
import com.ismail.mxreflection.models.MXFunction;
import com.ismail.mxreflection.utilities.ReflectionUtility;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * The factory responsible for the calculator initialization
 */
public class MXFactory {

    private MXFactory() {
    }

    /**
     * Generates a calculator to be used with the given type
     *
     * @param clazz The class type to be used with the calculator
     * @param <T>   The type to be used with the generated calculator
     * @return A class of type Calculator, the engine that is used to calculate functions
     */
    public static <T> MXCalculator<T> createCalculator(Class<T> clazz) {

        Map<String, MXFunction> annotatedFields = getAnnotatedExpressionFieldsAndValuesMap(clazz);

        if (!checkAnnotatedFields(annotatedFields))
            throw new NoExpressionFoundForTypeException(clazz.getName());

        FieldOrder fieldOrder = new FieldOrder(annotatedFields, clazz);

        return new MXCalculator<T>(fieldOrder);
    }

    private static boolean checkAnnotatedFields(Map annotatedFields) {
        return annotatedFields != null && !annotatedFields.isEmpty();
    }

    private static Map<String, MXFunction> getAnnotatedExpressionFieldsAndValuesMap(Class clazz) {

        return ReflectionUtility.getClassFields(clazz).stream().filter(f -> f.getAnnotation(Expression.class) != null)
                .map(f -> new MXFunction(f, clazz)).collect(Collectors.toMap(MXFunction::getArgumentName, f -> f));
    }

}
