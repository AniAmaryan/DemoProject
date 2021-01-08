package com.company.services;

import com.company.model.FilePaths;
import com.company.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * In this class there is registration, login or exit functions.
 *
 * @author Ani Amaryan
 */
public class MenuService {
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * This is main menu, in this stage user can register, login or exit, and if he choose
     * wrong operation this function restarting
     */
    public static void mainMenu() {
        welcomeMenu();

        int option = scanner.nextInt();
        UserService userService = new UserService();

        switch (option) {
            case 1 -> {
                User user = null;
                try {
                    user = userService.create();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                userService.writeAllData(user);
                mainMenu();
            }
            case 2 -> {
                try {
                    userService.loginUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case 3 -> {
                System.out.println("Thank you for using our online store");
                System.exit(0);
            }
            default -> System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    /**
     * In this menu there is registration login and exit operations.
     */
    private static void welcomeMenu() {
        System.out.println("-----Welcome to our online store-----");
        System.out.println();
        System.out.println("-----Menu-----");
        System.out.println("1. Registration");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Please enter the operation and end with the Enter key:");
    }

    /**
     * When the user login to his account, this menu opens, and asks to check account,
     * sell or buy products or back to previous menu.
     */
    public static void showUserActions(User user) {
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
                sellProducts(user);
                break;
            case 3:
                buyProducts(user);
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    /**
     * In this menu there is 'deposit', 'personal data' and 'back' operations.
     *
     * @param user
     */
    public static void myAccountMenu(User user) {
        System.out.println("-----My Account-----");
        System.out.println("1. Deposit");
        System.out.println("2. Personal Data");
        System.out.println("3. Back");
        System.out.println("Please enter the operation and end with the Enter key:");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                System.out.print("Please insert money: ");
                int moneyForDeposit = scanner.nextInt();
                FileService.changeBalance(FilePaths.USERS_PATH, user.getId(), moneyForDeposit);
                myAccountMenu(user);
            }
            case 2 -> {
                System.out.println(user.userPersonalData());
                myAccountMenu(user);
            }
            case 3 -> showUserActions(user);
            default -> System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    /**
     * This method shows all electronic types.
     */
    private static void displayProducts() {
        System.out.println("Please choose electronics");
        System.out.println("1. Notebook");
        System.out.println("2. PC");
        System.out.println("3. Phone");
        System.out.println("4. Tablet");
        System.out.println("5. TV");
        System.out.println("Please enter the operation and end with the Enter key:");
    }

    /**
     * In this method, the user selects an electronic type and sells it.The selected electronic is written
     * with the user ID in USER_PRODUCT_PATH.
     * @param user
     */
    private static void sellProducts(User user) {
        displayProducts();
        int option = scanner.nextInt();
        String date = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        switch (option) {
            case 1 -> {
                NotebookService notebookService = new NotebookService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + ","
                        + notebookService.createNotebook() + "," + date + "," + "TO_SELL");
                notebookService.writeAllData();
                showUserActions(user);
            }
            case 2 -> {
                PCService pcService = new PCService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + pcService.createPc()
                        + "," + date + "," + "TO_SELL");
                pcService.writeAllData();
                showUserActions(user);
            }
            case 3 -> {
                PhoneService phoneService = new PhoneService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + phoneService.createPhone()
                        + "," + date + "," + "TO_SELL");
                phoneService.writeAllData();
                showUserActions(user);
            }
            case 4 -> {
                TabletService tabletService = new TabletService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + tabletService.createTablet()
                        + "," + date + "," + "TO_SELL");
                tabletService.writeAllData();
                showUserActions(user);
            }
            case 5 -> {
                TVService tvService = new TVService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + tvService.createTV()
                        + "," + date + "," + "TO_SELL");
                tvService.writeAllData();
                showUserActions(user);
            }
            default -> System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    /**
     * In this method, the user selects an electronic type and make purchase.
     * @see PaymentService
     * @param user
     */
    private static void buyProducts(User user) {
        displayProducts();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                NotebookService notebookService = new NotebookService();
                notebookService.sortByPrice(notebookService.readNotebookData());
                makePurchase(user, "Notebook");
                showUserActions(user);
            }
            case 2 -> {
                PCService pcService = new PCService();
                pcService.sortByPrice(pcService.readPCData());
                makePurchase(user, "Pc");
                showUserActions(user);
            }
            case 3 -> {
                PhoneService phoneService = new PhoneService();
                phoneService.sortByPrice(phoneService.readPhoneData());
                makePurchase(user, "Phone");
                showUserActions(user);
            }
            case 4 -> {
                TabletService tabletService = new TabletService();
                tabletService.sortByPrice(tabletService.readTabletData());
                makePurchase(user, "Tablet");
                showUserActions(user);
            }
            case 5 -> {
                TVService tvService = new TVService();
                tvService.sortByPrice(tvService.readTVData());
                makePurchase(user, "Tv");
                showUserActions(user);
            }
            default -> System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    /**
     * In this stage user writes electronic id, that he want to buy.
     * @param user
     * @param electronicType
     */
    private static void makePurchase(User user, String electronicType) {
        System.out.println("Please insert id of product you want to buy :");
        String electronicId = scanner.next();
        PaymentService.makePayment(user, electronicId, electronicType);
    }
}