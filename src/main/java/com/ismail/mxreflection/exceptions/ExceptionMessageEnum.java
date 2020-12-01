package com.ismail.mxreflection.exceptions;

enum ExceptionMessageEnum {

    NO_EXPRESSION_FOUND_FOR_TYPE("No expression found for the given type: %s"),
    NOT_VALID_EXPRESSION("Not valid expression: %s for field %s: %s"),
    CYCLE_EXISTS_IN_CLASS("Cyclic relationship between expressions and arguments in type: %s"),
    DUPLICATE_ARGUMENT_NAME("A duplicated name used for different argument in type: %s"),
    ACCESS_NOT_ALLOWED_TO_READ("Can't access to read value for field: %s"),
    ACCESS_NOT_ALLOWED_TO_WRITE("Can't access to write value for field: %s"),
    UNPARSEABLE_FIELD_TYPE("Unparseable field ,of type: %s,  to Double"),
    UNPARSEABLE_FIELD_WRITE_TYPE("Can't parse Double to: %s"),
    NULL_FIELD_VALUE("Null field value: %s"),
    FIELD_WITH_NAME_NOT_FIND("Field not found with name: %s");

    private String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    String getMessage(){
        return message;
    }
}
