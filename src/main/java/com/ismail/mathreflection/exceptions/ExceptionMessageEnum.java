package com.ismail.mathreflection.exceptions;

public enum ExceptionMessageEnum {

    NO_FORMULA_FOUND_FOR_TYPE("No formula found for the given type: %s"),
    NOT_VALID_FORMULA("Not valid formula: %s for field %s: %s"),
    CYCLE_EXISTS_IN_CLASS("Cyclic relationship between formula and variables in type: %s"),
    DUPLICATE_VARIABLE_NAME("A duplicated name used for different variable in type: %s"),
    INVALID_SET_VARIABLES("Invalid set of variables");

    private String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
