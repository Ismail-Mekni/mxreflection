package com.ismailmekni.mxreflection.exceptions;

/**
 * Duplicated Argument Name Exception is raised when the given expression value in Expression annotation is not valid, missing arguments
 * or invalid operators
 */
public class NotValidExpressionException extends RuntimeException {

    public NotValidExpressionException(String... message) {
        super(String.format(ExceptionMessageEnum.NOT_VALID_EXPRESSION.getMessage(), message));
    }
}
