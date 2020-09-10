package com.ismail.mathreflection.exceptions;

public class NoFormulaFoundForTypeException extends RuntimeException {

    public NoFormulaFoundForTypeException() {
    }

    public NoFormulaFoundForTypeException(String message) {
        super(ExceptionMessageEnum.INVALID_SET_VARIABLES.getMessage());
    }

}
