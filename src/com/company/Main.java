package com.company;

import com.company.services.MenuService;

/**
 * This is online store application, and there is registration, login or exit functions.
 *
 * @author Ani Amaryan
 */
public class Main {

    public static void main(String[] args) throws Exception {

//        FileService.overwrite();
        MenuService.mainMenu();
    }
}