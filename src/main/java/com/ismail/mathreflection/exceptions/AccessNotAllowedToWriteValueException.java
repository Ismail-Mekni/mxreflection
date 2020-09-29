package com.ismail.mathreflection.exceptions;

/**
 * Access Not Allowed To Write Exception is raised when MathReflection can't access MXFormula annotated field
 * to set the result value
 */
public class AccessNotAllowedToWriteValueException extends RuntimeException {

    public AccessNotAllowedToWriteValueException() {
    }

    public AccessNotAllowedToWriteValueException(String message) {
        super(String.format(ExceptionMessageEnum.ACCESS_NOT_ALLOWED_TO_WRITE.getMessage(), message));
    }
}
