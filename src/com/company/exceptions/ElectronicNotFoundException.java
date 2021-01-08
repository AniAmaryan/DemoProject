package com.company.exceptions;

public class ElectronicNotFoundException extends Exception {
    public ElectronicNotFoundException() {
        super("Can't find electronic to buy");
    }
}
