package com.ismail.mathreflection.exceptions;

public class NoFormulaFoundForTypeException extends RuntimeException {

    public NoFormulaFoundForTypeException() {
    }

    public NoFormulaFoundForTypeException(String message) {
        super(String.format(ExceptionMessageEnum.NO_FORMULA_FOUND_FOR_TYPE.getMessage(), message));
    }

}
