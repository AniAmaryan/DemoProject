package com.company.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple password security using MD5 algorithm
 * @author Ani Amaryan
 */
public class MD5AlgorithmService {

    /**
     * Here is the password encoding method.
     * @param password
     * @return complete hashed string password in hex format
     */
    public static String md5(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}

