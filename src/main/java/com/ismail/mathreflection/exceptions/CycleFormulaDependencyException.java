package com.ismail.mathreflection.exceptions;

/**
 * Cycle Formula Dependency Exception is raised when there is a dependency cycle between the MXFormula annotated fields
 */
public class CycleFormulaDependencyException extends RuntimeException {

    public CycleFormulaDependencyException(String message) {
        super(String.format(ExceptionMessageEnum.CYCLE_EXISTS_IN_CLASS.getMessage(), message));
    }

    public CycleFormulaDependencyException() {
    }
}
