package com.company.services;

import com.company.exceptions.IntException;
import com.company.model.FilePaths;
import com.company.model.User;
import java.util.Scanner;

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
            case 1 -> {
                User user = userService.create();
                userService.writeAllData(user);
                mainMenu();
            }
            case 2 -> userService.loginUser();
            case 3 -> {
                System.out.println("Thank you for using our online store");
                System.exit(0);
            }
            default -> System.out.println("I'm Sorry,there is not the " + option + " option,please try again.");
        }
    }

    private static void welcomeMenu() {
        System.out.println("----------------------------------");
        System.out.println("\tWelcome to our online store\t");
        System.out.println("----------------------------------");
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
                try {
                    sellProducts(user);
                } catch (IntException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    buyProducts(user);
                } catch (IntException e) {
                    e.printStackTrace();
                }
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
                FileService.changeBalance(FilePaths.USERS_PATH, user.getId(), moneyForDeposit);
                myAccountMenu(user);
                break;
            case 2:
                System.out.println(user.userPersonalData());
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

    private static void displayProducts() {
        System.out.println("Please choose electronics");
        System.out.println("1. Notebook");
        System.out.println("2. PC");
        System.out.println("3. Phone");
        System.out.println("4. Tablet");
        System.out.println("5. TV");
        System.out.println("Please enter the operation and end with the Enter key:");
    }

    private static void buyProducts(User user) throws Exception {
        displayProducts();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                NotebookService notebookService = new NotebookService();
                notebookService.sortByPrice(notebookService.readNotebookData());
                makePurchase(user, "Notebook");
            }
            case 2 -> {
                PCService pcService = new PCService();
                pcService.sortByPrice(pcService.readPCData());
                makePurchase(user, "Pc");
            }
            case 3 -> {
                PhoneService phoneService = new PhoneService();
                phoneService.sortByPrice(phoneService.readPhoneData());
                makePurchase(user, "Phone");
            }
            case 4 -> {
                TabletService tabletService = new TabletService();
                tabletService.sortByPrice(tabletService.readTabletData());
                makePurchase(user, "Tablet");
            }
            case 5 -> {
                TVService tvService = new TVService();
                tvService.sortByPrice(tvService.readTVData());
                makePurchase(user, "Tv");
            }
        }
    }

    private static void sellProducts(User user) throws Exception {
        displayProducts();
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                NotebookService notebookService = new NotebookService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + notebookService.createNotebook());
                notebookService.writeAllData();
            }
            case 2 -> {
                PCService pcService = new PCService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + pcService.createPc());
                pcService.writeAllData();
            }
            case 3 -> {
                PhoneService phoneService = new PhoneService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + phoneService.createPhone());
                phoneService.writeAllData();
            }
            case 4 -> {
                TabletService tabletService = new TabletService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + tabletService.createTablet());
                tabletService.writeAllData();
            }
            case 5 -> {
                TVService tvService = new TVService();
                FileService.writeData(FilePaths.USER_PRODUCT_PATH, user.getId() + "," + tvService.createTV());
                tvService.writeAllData();
            }
        }
    }

    private static void makePurchase(User user, String electronicType) throws Exception {
        System.out.println("Please insert id of product you want to buy :");
        String electronicId = scanner.next();
        PaymentService.makePayment(user, electronicId, electronicType);
    }
}