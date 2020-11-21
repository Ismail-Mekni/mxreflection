package com.ismail.mxreflection.exceptions;

/**
 * Access Not Allowed To Write Exception is raised when MXReflection can't access MXFormula annotated field
 * to set the result value
 */
public class AccessNotAllowedToWriteValueException extends RuntimeException {

    public AccessNotAllowedToWriteValueException(String message) {
        super(String.format(ExceptionMessageEnum.ACCESS_NOT_ALLOWED_TO_WRITE.getMessage(), message));
    }
}
