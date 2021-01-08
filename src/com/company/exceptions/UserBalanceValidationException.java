package com.company.exceptions;

public class UserBalanceValidationException extends UserValidationException {
    public UserBalanceValidationException() {
        super("Invalid balance");
    }
}
