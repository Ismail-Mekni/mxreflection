package com.ismailmekni.mxreflection.factory;

import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.core.impl.MXCalculator;
import com.ismailmekni.mxreflection.exceptions.NoExpressionFoundForTypeException;
import com.ismailmekni.mxreflection.models.FieldOrder;
import com.ismailmekni.mxreflection.models.MXFunction;
import com.ismailmekni.mxreflection.core.ReflectionBean;

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

        ReflectionBean reflectionBean = new ReflectionBean(clazz);

        Map<String, MXFunction> annotatedFields = getAnnotatedExpressionFieldsAndValuesMap(reflectionBean);

        if (!checkAnnotatedFields(annotatedFields))
            throw new NoExpressionFoundForTypeException(clazz.getName());

        FieldOrder fieldOrder = new FieldOrder(annotatedFields, reflectionBean);

        return new MXCalculator<T>(fieldOrder, reflectionBean);
    }

    private static boolean checkAnnotatedFields(Map annotatedFields) {
        return annotatedFields != null && !annotatedFields.isEmpty();
    }

    private static Map<String, MXFunction> getAnnotatedExpressionFieldsAndValuesMap(ReflectionBean reflectionBean) {

        return reflectionBean.getFields().stream().filter(f -> f.getAnnotation(Expression.class) != null)
                .map(f -> new MXFunction(f, reflectionBean)).collect(Collectors.toMap(MXFunction::getArgumentName, f -> f));
    }

}
