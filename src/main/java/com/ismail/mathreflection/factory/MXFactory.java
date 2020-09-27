package com.ismail.mathreflection.factory;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.exceptions.NoFormulaFoundForTypeException;
import com.ismail.mathreflection.models.FieldOrder;
import com.ismail.mathreflection.models.MXFunction;
import com.ismail.mathreflection.utilities.ReflectionUtility;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * The factory responsible for the calculator initialization
 */
public class MXFactory {

    /**
     * Generates a calculator to be used with the given type
     *
     * @param clazz The class type to be used with the calculator
     * @param <T> The type to be used with the generated calculator
     * @return A class of type Calculator, the engine that is used to calculate functions
     */
    public static <T> MXCalculator<T> createCalculator(Class<T> clazz) {

        Map<String, MXFunction> annotatedFields = getAnnotatedMXFormulaFieldsAndValuesMap(clazz);

        if( !checkAnnotatedFields(annotatedFields))
            throw new NoFormulaFoundForTypeException(clazz.getName());

        FieldOrder fieldOrder=new FieldOrder(annotatedFields, clazz);

        return new MXCalculator<T>(fieldOrder);
    }

    private static boolean checkAnnotatedFields(Map annotatedFields) {
        return annotatedFields != null && !annotatedFields.isEmpty();
    }

    private static Map<String, MXFunction> getAnnotatedMXFormulaFieldsAndValuesMap(Class clazz){

        return ReflectionUtility.getClassFields(clazz).stream().filter(f -> f.getAnnotation(MXFormula.class) != null)
                .map(f -> new MXFunction(f, clazz)).collect(Collectors.toMap(MXFunction::getFieldName, f->f));
    }

}
