package com.ismail.mathreflection.exceptions;

public class AccessNotAllowedToReadException extends RuntimeException{

    public AccessNotAllowedToReadException() {
    }

    public AccessNotAllowedToReadException(String... message) {
        super(String.format(ExceptionMessageEnum.ACCESS_NOT_ALLOWED_TO_READ.getMessage(), message));
    }
}
