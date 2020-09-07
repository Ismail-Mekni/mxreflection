package com.ismail.mathreflection.exceptions;

public class CycleFormulaDependencyException extends RuntimeException {
    public CycleFormulaDependencyException(String message) {
        super(String.format(ExceptionMessageEnum.CYCLE_EXISTS_IN_CLASS.getMessage(), message));
    }

    public CycleFormulaDependencyException() {
    }
}
