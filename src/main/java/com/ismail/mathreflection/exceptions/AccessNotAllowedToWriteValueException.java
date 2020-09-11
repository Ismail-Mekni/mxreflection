package com.ismail.mathreflection.exceptions;

public class AccessNotAllowedToWriteValueException extends RuntimeException {

    public AccessNotAllowedToWriteValueException() {
    }

    public AccessNotAllowedToWriteValueException(String message) {
        super(String.format(ExceptionMessageEnum.ACCESS_NOT_ALLOWED_TO_WRITE.getMessage(), message));
    }
}
