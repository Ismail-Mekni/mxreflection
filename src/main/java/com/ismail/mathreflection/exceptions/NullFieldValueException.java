package com.ismail.mathreflection.exceptions;

public class NullFieldValueException extends RuntimeException {

    public NullFieldValueException(String message) {
        super(String.format(ExceptionMessageEnum.NULL_FIELD_VALUE.getMessage(), message));
    }

    public NullFieldValueException() {
    }
}
