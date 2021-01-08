package com.company.exceptions;

public class UserValidationException extends Exception {
    public UserValidationException(String massage) {
        super(massage);
    }
}
