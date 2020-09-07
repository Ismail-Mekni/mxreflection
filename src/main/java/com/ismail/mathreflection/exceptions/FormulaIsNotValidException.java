package com.ismail.mathreflection.exceptions;

public class FormulaIsNotValidException extends RuntimeException {
    public FormulaIsNotValidException() {
    }

    public FormulaIsNotValidException(String... message) {
        super(String.format(ExceptionMessageEnum.NOT_VALID_FORMULA.getMessage(), message));
    }
}
