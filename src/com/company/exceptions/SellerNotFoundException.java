package com.company.exceptions;

public class SellerNotFoundException extends Exception {
    public SellerNotFoundException(){
        super("Seller not found");
    }
}
