package com.ismail.mathreflection.exceptions;

public class InvalidSetOfVariableException extends RuntimeException {

    public InvalidSetOfVariableException() {
    }

    public InvalidSetOfVariableException(String message) {
        super(ExceptionMessageEnum.INVALID_SET_VARIABLES.getMessage());
    }
}
