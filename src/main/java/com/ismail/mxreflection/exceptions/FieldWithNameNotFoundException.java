package com.ismail.mxreflection.exceptions;

/**
 * Field not found with a given value of argument annotation
 * Note: This exception indicates an internal error in the framework
 */
public class FieldWithNameNotFoundException extends RuntimeException {

    public FieldWithNameNotFoundException(String message) {
        super(String.format(ExceptionMessageEnum.FIELD_WITH_NAME_NOT_FIND.getMessage(), message));
    }
}
