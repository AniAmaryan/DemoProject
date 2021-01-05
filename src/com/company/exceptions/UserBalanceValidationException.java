package com.company.exceptions;

public class UserBalanceValidationException extends Exception {
    public UserBalanceValidationException(){
        super("Invalid balance");
    }
}
