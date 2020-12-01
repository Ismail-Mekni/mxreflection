package com.ismail.mxreflection.exceptions;

/**
 * No Expression Found For Type Exception is raised when there is no Expression annotated field in the given class type
 */
public class NoExpressionFoundForTypeException extends RuntimeException {

    public NoExpressionFoundForTypeException(String message) {
        super(ExceptionMessageEnum.NO_EXPRESSION_FOUND_FOR_TYPE.getMessage());
    }

}
