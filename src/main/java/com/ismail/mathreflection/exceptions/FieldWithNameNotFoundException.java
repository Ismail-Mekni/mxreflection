package com.ismail.mathreflection.exceptions;

public class FieldWithNameNotFoundException extends RuntimeException {

    public FieldWithNameNotFoundException(String message) {
        super(String.format(ExceptionMessageEnum.FIELD_WITH_NAME_NOT_FIND.getMessage(), message));
    }
}
