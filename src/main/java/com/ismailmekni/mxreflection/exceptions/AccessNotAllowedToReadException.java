package com.ismailmekni.mxreflection.exceptions;

/**
 * Access Not Allowed To Read Exception is raised when MXReflection can't access an argument value
 */
public class AccessNotAllowedToReadException extends RuntimeException{

    public AccessNotAllowedToReadException(String message) {
        super(String.format(ExceptionMessageEnum.ACCESS_NOT_ALLOWED_TO_READ.getMessage(), message));
    }
}
