package com.ismail.mxreflection.exceptions;

/**
 * Unparseable Field Exception is raised when a given variable field value is not numeric
 */
public class UnparseableFieldException extends RuntimeException {

    public UnparseableFieldException(String... message) {
        super(String.format(ExceptionMessageEnum.UNPARSEABLE_FIELD_TYPE.getMessage(), message));
    }

}
