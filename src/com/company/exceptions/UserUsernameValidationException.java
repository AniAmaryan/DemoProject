package com.company.exceptions;

public class UserUsernameValidationException extends UserValidationException {
    public UserUsernameValidationException() {
        super("Invalid username");
    }
}
