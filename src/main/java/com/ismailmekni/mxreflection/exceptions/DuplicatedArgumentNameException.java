package com.ismailmekni.mxreflection.exceptions;

/**
 * Duplicated Argument Name Exception is raised when there is two Argument annotated fields with the same value as name
 */
public class DuplicatedArgumentNameException extends RuntimeException {

    public DuplicatedArgumentNameException(String message) {
        super(String.format(ExceptionMessageEnum.DUPLICATE_ARGUMENT_NAME.getMessage(), message));
    }
}
