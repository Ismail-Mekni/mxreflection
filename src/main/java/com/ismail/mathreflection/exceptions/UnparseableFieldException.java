package com.ismail.mathreflection.exceptions;

public class UnparseableFieldException extends RuntimeException {

    public UnparseableFieldException(String... message) {
        super(String.format(ExceptionMessageEnum.UNPARSEABLE_FIELD_TYPE.getMessage(), message));
    }

    public UnparseableFieldException() {
    }
}
