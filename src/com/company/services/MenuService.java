package com.company.services;

import com.company.model.User;

import java.util.Scanner;
import java.util.UUID;

public class MenuService {
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * In this stage user can register, login or exit , and if he choose wrong operation this function restarting
     *
     * @throws Exception
     */
    public static void mainMenu() throws Exception {
        welcomeMenu();

        int option = scanner.nextInt();
        UserService userService = new UserService();

        switch (option) {
            case 1:
                User user = userService.create();
                userService.writeAllData(user);
                break;
            case 2:
                userService.loginUser();
                break;
            case 3:
                System.out.println("Thank you for using our online store");
                System.exit(0);
                break;
            default:
                System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    private static void welcomeMenu() {
        System.out.println("----------------------------------");
        System.out.println("\tWelcome to our online store\t");
        System.out.println("----------------------------------");

        System.out.println("-----Menu-----");
        System.out.println("1. Registration");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Please enter the operation and end with the Enter key:");
    }

    /**
     * When the user login to his account, this menu opens, and asks to check account,
     * sell or buy products or back to previous menu.
     *
     * @throws Exception
     */
    public static void showUserActions(User user) throws Exception {
        System.out.println("-----Menu-----");
        System.out.println("1. My account");
        System.out.println("2. Sell products");
        System.out.println("3. Buy products");
        System.out.println("4. Back");
        System.out.println("Please enter the operation and end with the Enter key:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                myAccountMenu(user);
                break;
            case 2:
                //go to sell products page
                break;
            case 3:
                //go to buy products page
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    public static void myAccountMenu(User user) throws Exception {
        System.out.println("-----My Account-----");
        System.out.println("1. Deposit");
        System.out.println("2. Personal Data");
        System.out.println("3. My Products");
        System.out.println("4. Back");
        System.out.println("Please enter the operation and end with the Enter key:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.print("Please insert money: ");
                int moneyForDeposit = scanner.nextInt();
                FileService.changeBalance(user, moneyForDeposit);
                myAccountMenu(user);
                break;
            case 2:
                System.out.println(user.toString());
                myAccountMenu(user);
                break;
            case 3:
                //My Products function
                break;
            case 4:
                showUserActions(user);
                break;
            default:
                System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }
}
