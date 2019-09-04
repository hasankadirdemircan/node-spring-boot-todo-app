package com.hkdemircan.todoapp.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Error {


    ERR999("An unknown error has occurred. Please contact us."),
    ERR998("No privilege for this operation."),
    ERR997("Request is incorrect."),

    ERR001("Username / password is not valid."),
    ERR002("User has no role.");

    private final String error;

    Error(final String error) {
        this.error = error;
    }

    @JsonValue
    public String getError() {
        return this.name() + " - " + error;
    }

}
