package com.ismail.mathreflection.exceptions;

public class UnparseableResultException extends RuntimeException {

    public UnparseableResultException(String message) {
        super(String.format(ExceptionMessageEnum.UNPARSEABLE_FIELD_WRITE_TYPE.getMessage(), message));
    }

    public UnparseableResultException() {
    }
}
