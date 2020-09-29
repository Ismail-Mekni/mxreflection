package com.ismail.mathreflection.exceptions;

/**
 * Null Field Value Exception is raised when a given variable field is null
 */
public class NullFieldValueException extends RuntimeException {

    public NullFieldValueException(String message) {
        super(String.format(ExceptionMessageEnum.NULL_FIELD_VALUE.getMessage(), message));
    }

    public NullFieldValueException() {
    }
}
