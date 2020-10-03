package com.ismail.mathreflection.exceptions;

/**
 * Unparseable Result Exception is raised when the MXFormula annotated field is within valid type collection (Long, Double,
 * BigInteger, String)
 */
public class UnparseableResultException extends RuntimeException {

    public UnparseableResultException(String message) {
        super(String.format(ExceptionMessageEnum.UNPARSEABLE_FIELD_WRITE_TYPE.getMessage(), message));
    }

}
