package com.ismail.mathreflection.exceptions;

public class DuplicatedVariableNameException extends RuntimeException {

    public DuplicatedVariableNameException(String message) {
        super(String.format(ExceptionMessageEnum.DUPLICATE_VARIABLE_NAME.getMessage(), message));
    }

    public DuplicatedVariableNameException() {
    }
}
