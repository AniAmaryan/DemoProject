package com.company.services;

import com.company.exceptions.UserBalanceValidationException;
import com.company.exceptions.UserFullNameValidationException;
import com.company.exceptions.UserPasswordValidationException;
import com.company.exceptions.UserUsernameValidationException;
import com.company.model.FilePaths;
import com.company.model.User;
import java.io.IOException;
import java.util.List;

/**
 * This class for validation
 */
public class UserValidationService {

    /**
     * This method checks if the email is valid or not with regex
     * @param email
     */
    protected static void isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(regex)) {
            System.out.print("Invalid email");
        }
    }

    /**
     * This method checks if the full name contain only letters
     * @param fullName
     */
    protected static void isValidFullName(String fullName) throws Exception {
        String regex = "^[\\p{L} .'-]+$";
        if (!fullName.matches(regex)) {
            throw new UserFullNameValidationException();
        }
    }

    /**
     * This method check password
     * @param password
     */
    protected static void isValidPassword(String password) throws Exception {
        int countOfUppercase = 0;
        int countOfDigits = 0;
        char chars;
        for (int i = 0; i < password.length(); i++) {
            chars = password.charAt(i);
            if (Character.isUpperCase(chars)) {
                countOfUppercase += 1;
            } else if (Character.isDigit(chars)) {
                countOfDigits += 1;
            }
        }
        if (password.length() < 8 || countOfUppercase < 2 || countOfDigits < 3) {
            throw new UserPasswordValidationException();
        }
    }

    /**
     * This method check balance
     * @param balance
     */
    protected static void isValidBalance(int balance) throws UserBalanceValidationException {
        if(balance <0){
           throw new UserBalanceValidationException();
        }
    }

    /**
     * This method check username
     * @param username
     * @throws IOException
     */
    protected static void isValidUsername(String username) throws Exception {
        if (!isValidUsernameLength(username) || !isUsernameDuplicate(username)) {
            throw new UserUsernameValidationException();
        }
    }

    /**
     * This method check username length
     * @param username
     * @return
     */
    protected static boolean isValidUsernameLength(String username) {
        if (username.length() == 0) {
            System.out.println("Your entered username was empty!!!");
            return false;
        }
        return username.length() >= 10;
    }

    /**
     * This method check if the username have duplicate in database
     * @param username
     * @return
     * @throws IOException
     */
    protected static boolean isUsernameDuplicate(String username) throws IOException {
        List<String> read = FileService.read(FilePaths.USERS_PATH);

        for (String s : read) {
            String[] line = s.split(",");
            if (line.length > 6) {
                if (line[1].equals(username)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checking user
     * @param user
     * @throws IOException
     */
    public static void isValidUser(User user) throws Exception {
        isValidFullName(user.getFullName());
        isValidUsername(user.getUsername());
        isValidEmail(user.getEmail());
        isValidPassword(user.getPassword());
        isValidBalance(user.getBalance());
    }
}
