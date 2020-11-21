package com.ismail.mxreflection.exceptions;

/**
 * Access Not Allowed To Read Exception is raised when MXReflection can't access a variable value
 */
public class AccessNotAllowedToReadException extends RuntimeException{

    public AccessNotAllowedToReadException(String message) {
        super(String.format(ExceptionMessageEnum.ACCESS_NOT_ALLOWED_TO_READ.getMessage(), message));
    }
}
