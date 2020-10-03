package com.ismail.mathreflection.exceptions;

/**
 * Duplicated Variable Name Exception is raised when there is two Variable annotated fields with the same value as name
 */
public class DuplicatedVariableNameException extends RuntimeException {

    public DuplicatedVariableNameException(String message) {
        super(String.format(ExceptionMessageEnum.DUPLICATE_VARIABLE_NAME.getMessage(), message));
    }
}
