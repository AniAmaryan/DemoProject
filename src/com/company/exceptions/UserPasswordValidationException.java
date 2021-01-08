package com.company.exceptions;

public class UserPasswordValidationException extends UserValidationException {
    public UserPasswordValidationException() {
        super("Invalid password");
    }
}
