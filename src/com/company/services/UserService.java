package com.company.services;

import com.company.Main;
import com.company.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This class works with User class.
 *
 * @author Ani Amaryan
 */
public class UserService {
    private static final String PATH = "C:\\Users\\User\\IdeaProjects\\armBay\\src\\com\\company\\users.txt";
    private final Scanner scanner = new Scanner(System.in);
    User user;

    /**
     * With help Scanner class User can write his personal data.
     *
     * @return new User
     * @throws IOException
     */
    public User create() throws IOException {
        user = new User();
        System.out.println("Please enter your full name");
        user.setFullName(scanner.nextLine());
        System.out.println("Please enter username (It's length must be more than 10)");
        user.setUsername(scanner.nextLine());
        System.out.println("Please enter your email");
        user.setEmail(scanner.nextLine());
        System.out.println("Please enter password (It's length must be more than 8," +
                " contain 2 uppercase letters and 3 numbers)");
        user.setPassword(scanner.nextLine());
        System.out.println("Please enter balance");
        user.setBalance(scanner.nextInt());

        UserValidationService.isValidUser(user);

        return new User(user.getFullName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getBalance());
    }

    /**
     * This method add the user data to the database.
     *
     * @param user
     * @throws Exception
     */
    public void writeAllData(User user) throws Exception {
        FileService.write(PATH, user.toString());
    }

    /**
     * This method for login, if user have an account he redirects in menu page for buying,
     * selling or for checking his account.
     *
     * @throws IOException
     */
    public void loginUser() throws Exception {
        System.out.println("Please enter your username");
        String userName = scanner.nextLine();
        System.out.println("Please enter your password");
        String password = scanner.nextLine();
        List<String> read = FileService.read(PATH);
        boolean isLoginSuccess = false;
        User user = new User();
        for (String s : read) {
            user = new User();
            String[] line = s.split(",");
            user.setId(UUID.fromString(line[0]));
            user.setFullName(line[1]);
            user.setUsername(line[2]);
            user.setEmail(line[3]);
            user.setPassword(line[4]);
            user.setBalance(Integer.parseInt(line[5]));
            if (user.getUsername().equals(userName) &&
                    user.getPassword().equals(MD5AlgorithmService.md5(password))) {
                isLoginSuccess = true;
                break;
            }
        }
        if (isLoginSuccess) {
            MenuService.showUserActions(user);
        } else {
            System.out.println("Invalid input data");
        }
    }
}
