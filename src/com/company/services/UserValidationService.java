package com.company.services;

import com.company.model.User;
import java.io.IOException;
import java.util.List;

/**
 * This class for validation
 */
public class UserValidationService {
    private static final String PATH = "C:\\Users\\User\\IdeaProjects\\armBay\\src\\com\\company\\users.txt";

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
    protected static void isValidFullName(String fullName) {
        String regex = "^[\\p{L} .'-]+$";
        if (!fullName.matches(regex)) {
            System.out.print("Invalid full name");
        }
    }

    /**
     * This method check password
     * @param password
     */
    protected static void isValidPassword(String password) {
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
            System.out.print("Invalid password");
        }
    }

    /**
     * This method check username
     * @param username
     * @throws IOException
     */
    protected static void isValidUsername(String username) throws IOException {
        if (!isValidUsernameLength(username) || !isUsernameDuplicate(username)) {
            System.out.print("Invalid username");
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
        List<String> read = FileService.read(PATH);

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
    public static void isValidUser(User user) throws IOException {
        isValidUsername(user.getUsername());
        isValidPassword(user.getPassword());
        isValidEmail(user.getEmail());
        isValidFullName(user.getFullName());
    }
}
