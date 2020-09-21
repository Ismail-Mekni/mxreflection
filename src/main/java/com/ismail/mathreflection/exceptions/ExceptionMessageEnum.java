package com.ismail.mathreflection.exceptions;

public enum ExceptionMessageEnum {

    NO_FORMULA_FOUND_FOR_TYPE("No formula found for the given type: %s"),
    NOT_VALID_FORMULA("Not valid formula: %s for field %s: %s"),
    CYCLE_EXISTS_IN_CLASS("Cyclic relationship between formula and variables in type: %s"),
    DUPLICATE_VARIABLE_NAME("A duplicated name used for different variable in type: %s"),
    INVALID_SET_VARIABLES("Invalid set of variables"),
    ACCESS_NOT_ALLOWED_TO_READ("Can't access to read value, may be you are missing getter, for field: %s"),
    ACCESS_NOT_ALLOWED_TO_WRITE("Can't access to write value, may be you are missing setter, for field: %s"),
    UNPARSEABLE_FIELD_TYPE("Unparseable field ,of type: %s,  to Double"),
    UNPARSEABLE_FIELD_WRITE_TYPE("Can't parse Double to: %s"),
    NULL_FIELD_VALUE("Null field value: %s");

    private String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
