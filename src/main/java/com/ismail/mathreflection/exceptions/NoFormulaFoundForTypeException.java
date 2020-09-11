package com.ismail.mathreflection.exceptions;

public class NoFormulaFoundForTypeException extends RuntimeException {

    public NoFormulaFoundForTypeException() {
    }

    public NoFormulaFoundForTypeException(String message) {
        super(ExceptionMessageEnum.NO_FORMULA_FOUND_FOR_TYPE.getMessage());
    }

}
