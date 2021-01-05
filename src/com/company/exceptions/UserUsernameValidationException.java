package com.company.exceptions;

public class UserUsernameValidationException extends Exception{
    public UserUsernameValidationException(){
        super("Invalid username");
    }
}
