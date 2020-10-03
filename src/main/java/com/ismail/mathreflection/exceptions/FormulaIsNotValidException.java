package com.ismail.mathreflection.exceptions;

/**
 * Duplicated Variable Name Exception is raised when the given formula in MXFormula is not valid, missing variables
 * or invalid operators
 */
public class FormulaIsNotValidException extends RuntimeException {

    public FormulaIsNotValidException(String... message) {
        super(String.format(ExceptionMessageEnum.NOT_VALID_FORMULA.getMessage(), message));
    }
}
