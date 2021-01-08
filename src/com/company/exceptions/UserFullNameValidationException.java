package com.company.exceptions;

public class UserFullNameValidationException extends UserValidationException {
    public UserFullNameValidationException() {
        super("Invalid full name");
    }
}
