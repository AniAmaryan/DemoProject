package com.company.exceptions;

public class UserEmailValidationException extends UserValidationException {
    public UserEmailValidationException() {
        super("Invalid email");
    }
}
