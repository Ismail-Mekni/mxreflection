package com.ismail.mathreflection.exceptions;

/**
 * No Formula Found For Type Exception is raised when there is no MXFormula annotated field in the given class type
 */
public class NoFormulaFoundForTypeException extends RuntimeException {

    public NoFormulaFoundForTypeException(String message) {
        super(ExceptionMessageEnum.NO_FORMULA_FOUND_FOR_TYPE.getMessage());
    }

}
