package com.company.exceptions;

public class UserFullNameValidationException extends Exception{
    public UserFullNameValidationException(){
        super("Invalid full name");
    }
}
