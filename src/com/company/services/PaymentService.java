package com.company.services;

import com.company.exceptions.ElectronicNotFoundException;
import com.company.exceptions.NotEnoughFoundsException;
import com.company.exceptions.SellerNotFoundException;
import com.company.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * This class for payment.
 *
 * @author Ani Amaryan
 */
public class PaymentService {

    /**
     * This method gets from params buyer, electronic id and electronic type. And depend electronic type
     * param sends it to the right service.
     *
     * @param buyer
     * @param electronicId
     * @param electronicType
     * @throws Exception
     */
    public static void makePayment(User buyer, String electronicId, String electronicType) {
        Electronics electronic = null;
        User seller = null;
        switch (electronicType) {
            case "Notebook" -> {
                NotebookService notebookService = new NotebookService();
                Notebook[] notebooks = notebookService.readNotebookData();
                for (Notebook notebook : notebooks) {
                    if (String.valueOf(notebook.getId()).equals(electronicId)) {
                        electronic = notebook;
                        break;
                    }
                }
                seller = findSeller(electronic);
                try {
                    paymentValidation(buyer, seller, electronic);
                } catch (ElectronicNotFoundException | NotEnoughFoundsException | SellerNotFoundException e) {
                    e.printStackTrace();
                }
                makeTransaction(FilePaths.NOTEBOOK_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
            }
            case "Pc" -> {
                PCService pcService = new PCService();
                PC[] pcs = pcService.readPCData();
                for (PC pc : pcs) {
                    if (String.valueOf(pc.getId()).equals(electronicId)) {
                        electronic = pc;
                        break;
                    }
                }
                seller = findSeller(electronic);
                try {
                    paymentValidation(buyer, seller, electronic);
                } catch (ElectronicNotFoundException | NotEnoughFoundsException | SellerNotFoundException e) {
                    e.printStackTrace();
                }
                makeTransaction(FilePaths.PC_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
            }
            case "Phone" -> {
                PhoneService phoneService = new PhoneService();
                Phone[] phones = phoneService.readPhoneData();
                for (Phone phone : phones) {
                    if (String.valueOf(phone.getId()).equals(electronicId)) {
                        electronic = phone;
                        break;
                    }
                }
                seller = findSeller(electronic);
                try {
                    paymentValidation(buyer, seller, electronic);
                } catch (ElectronicNotFoundException | NotEnoughFoundsException | SellerNotFoundException e) {
                    e.printStackTrace();
                }
                makeTransaction(FilePaths.PHONE_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
            }
            case "Tablet" -> {
                TabletService tabletService = new TabletService();
                Tablet[] tablets = tabletService.readTabletData();
                for (Tablet tablet : tablets) {
                    if (String.valueOf(tablet.getId()).equals(electronicId)) {
                        electronic = tablet;
                        break;
                    }
                }
                seller = findSeller(electronic);
                try {
                    paymentValidation(buyer, seller, electronic);
                } catch (ElectronicNotFoundException | NotEnoughFoundsException | SellerNotFoundException e) {
                    e.printStackTrace();
                }
                makeTransaction(FilePaths.TABLET_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
            }
            case "Tv" -> {
                TVService tvService = new TVService();
                TV[] tvs = tvService.readTVData();
                for (TV tv : tvs) {
                    if (String.valueOf(tv.getId()).equals(electronicId)) {
                        electronic = tv;
                        break;
                    }
                }
                seller = findSeller(electronic);
                try {
                    paymentValidation(buyer, seller, electronic);
                } catch (ElectronicNotFoundException | NotEnoughFoundsException | SellerNotFoundException e) {
                    e.printStackTrace();
                }
                makeTransaction(FilePaths.TV_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
            }
            default -> System.out.println("I'm Sorry,there is not the " + electronicType + " option,please try again.");
        }
    }

    /**
     * This method checks if there is electronics to buy, the balance is enough or not
     * and checks seller and throws exceptions.
     *
     * @param buyer
     * @param seller
     * @param electronicsToBuy
     * @throws Exception
     */
    private static void paymentValidation(User buyer, User seller, Electronics electronicsToBuy)
            throws ElectronicNotFoundException, NotEnoughFoundsException, SellerNotFoundException {
        if (electronicsToBuy == null) {
            throw new ElectronicNotFoundException();
        }
        if (buyer.getBalance() < electronicsToBuy.getPrice()) {
            throw new NotEnoughFoundsException();
        }
        if (seller == null || seller.getId() == null) {
            throw new SellerNotFoundException();
        }
    }

    /**
     * This method calls every method that are responsible for transaction.
     *
     * @param electronicsPath
     * @param buyerId
     * @param sellerId
     * @param electronicId
     * @param electronicPrice
     */
    private static void makeTransaction(String electronicsPath, UUID buyerId, UUID sellerId, UUID electronicId,
                                        int electronicPrice) {
        FileService.changeBalance(FilePaths.USERS_PATH, buyerId, -electronicPrice);
        FileService.changeBalance(FilePaths.USERS_PATH, sellerId, electronicPrice);
        FileService.changeUserId(FilePaths.USER_PRODUCT_PATH, String.valueOf(buyerId), String.valueOf(electronicId));
        FileService.changeProductStatus(electronicsPath, String.valueOf(electronicId));
    }

    /**
     * This method find seller by user id from user product list.
     *
     * @param electronics
     * @return
     */
    private static User findSeller(Electronics electronics) {
        User seller = new User();
        List<String> users = null;
        try {
            users = FileService.read(FilePaths.USER_PRODUCT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (electronics != null) {
            for (String user : users) {
                String[] line = user.split(",");
                if (line[1].equals(String.valueOf(electronics.getId()))) {
                    seller.setId(UUID.fromString(line[0]));
                }
            }
        }
        return seller;
    }
}