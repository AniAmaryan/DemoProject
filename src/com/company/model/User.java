package com.company.model;

import com.company.services.MD5AlgorithmService;

import java.util.List;
import java.util.UUID;

/**
 * The User class is a simple container for user data.
 * @author Ani amaryan
 */
public class User {
    public UUID id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private int balance;
    private List<String> userProducts;


    /**
     * toString returns string for writing in database.
     * @return id, full name, username, email, password and balance
     */
    @Override
    public String toString() {
        return getId() + "," + getFullName() + "," + getUsername() + "," + getEmail() + ","
                + getPassword() + "," + getBalance() + "\n";
    }

    //Add personal data method without pass

    public User(String fullName, String username, String email, String password, int balance) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = MD5AlgorithmService.md5(password);
        this.balance = balance;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<String> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<String> userProducts) {
        this.userProducts = userProducts;
    }
}
