package com.company.exceptions;

public class UserPasswordValidationException extends Exception {
    public UserPasswordValidationException(){
        super("Invalid password");
    }
}
