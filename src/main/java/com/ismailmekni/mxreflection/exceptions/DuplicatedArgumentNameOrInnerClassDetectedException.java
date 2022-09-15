package com.ismailmekni.mxreflection.exceptions;

/**
 * Duplicated Argument Name Exception is raised when there is two Argument annotated fields with the same value as name
 */
public class DuplicatedArgumentNameOrInnerClassDetectedException extends RuntimeException {

    public DuplicatedArgumentNameOrInnerClassDetectedException(String message) {
        super(String.format(ExceptionMessageEnum.DUPLICATE_ARGUMENT_NAME_OR_INNER_CLASS.getMessage(), message));
    }
}
