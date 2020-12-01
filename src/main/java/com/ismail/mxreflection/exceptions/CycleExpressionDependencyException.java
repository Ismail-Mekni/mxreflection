package com.ismail.mxreflection.exceptions;

/**
 * Cycle Expression Dependency Exception is raised when there is a dependency cycle between the Expression annotated fields
 */
public class CycleExpressionDependencyException extends RuntimeException {

    public CycleExpressionDependencyException(String message) {
        super(String.format(ExceptionMessageEnum.CYCLE_EXISTS_IN_CLASS.getMessage(), message));
    }
}
