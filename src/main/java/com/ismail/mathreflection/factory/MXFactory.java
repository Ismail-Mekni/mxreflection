package com.ismail.mathreflection.factory;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.core.Calculator;
import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.exceptions.NoFormulaFoundForTypeException;
import com.ismail.mathreflection.models.FieldOrder;
import com.ismail.mathreflection.models.MXFunction;
import com.ismail.mathreflection.utilities.ReflectionUtility;

import java.util.Map;
import java.util.stream.Collectors;

public class MXFactory {

    public static Calculator createCalculator(Class clazz) {

        Map<String, MXFunction> annotatedFields = getAnnotatedMXFormulaFieldsAndValuesMap(clazz);



        if( !checkAnnotatedFields(annotatedFields))
            throw new NoFormulaFoundForTypeException(clazz.getName());

        FieldOrder fieldOrder=new FieldOrder(annotatedFields, clazz);

        Calculator calculator=new MXCalculator();
        return calculator;
    }

    private static boolean checkAnnotatedFields(Map annotatedFields) {
        return annotatedFields != null && !annotatedFields.isEmpty();
    }

    private static Map<String, MXFunction> getAnnotatedMXFormulaFieldsAndValuesMap(Class clazz){

        return ReflectionUtility.getClassFields(clazz).stream().filter(f -> f.getAnnotation(MXFormula.class) != null)
                .map(f -> new MXFunction(f, clazz)).collect(Collectors.toMap(MXFunction::getFieldName, f->f));
    }

}
