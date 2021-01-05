package com.company.services;

import com.company.exceptions.ElectronicNotFoundException;
import com.company.exceptions.NotEnoughFoundsException;
import com.company.exceptions.SellerNotFoundException;
import com.company.model.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PaymentService {

    public static void makePayment(User buyer, String electronicId, String electronicType) throws Exception {
        Electronics electronic = null;
        User seller = null;
        switch (electronicType) {
            case "Notebook":
                NotebookService notebookService = new NotebookService();
                Notebook[] notebooks = notebookService.readNotebookData();
                for (Notebook notebook : notebooks) {
                    if (String.valueOf(notebook.getId()).equals(electronicId)) {
                        electronic = notebook;
                        break;
                    }
                }
                seller = findSeller(electronic);
                paymentValidation(buyer, seller, electronic);
                makeTransaction(FilePaths.NOTEBOOK_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
                break;
            case "Pc":
                PCService pcService = new PCService();
                PC[] pcs = pcService.readPCData();
                for (PC pc : pcs) {
                    if (String.valueOf(pc.getId()).equals(electronicId)) {
                        electronic = pc;
                        break;
                    }
                }
                seller = findSeller(electronic);
                paymentValidation(buyer, seller, electronic);
                makeTransaction(FilePaths.PC_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
                break;
            case "Phone":
                PhoneService phoneService = new PhoneService();
                Phone[] phones = phoneService.readPhoneData();
                for (Phone phone : phones) {
                    if (String.valueOf(phone.getId()).equals(electronicId)) {
                        electronic = phone;
                        break;
                    }
                }
                seller = findSeller(electronic);
                paymentValidation(buyer, seller, electronic);
                makeTransaction(FilePaths.PHONE_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
                break;
            case "Tablet":
                TabletService tabletService = new TabletService();
                Tablet[] tablets = tabletService.readTabletData();
                for (Tablet tablet : tablets) {
                    if (String.valueOf(tablet.getId()).equals(electronicId)) {
                        electronic = tablet;
                        break;
                    }
                }
                seller = findSeller(electronic);
                paymentValidation(buyer, seller, electronic);
                makeTransaction(FilePaths.TABLET_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
                break;
            case "Tv":
                TVService tvService = new TVService();
                TV[] tvs = tvService.readTVData();
                for (TV tv : tvs) {
                    if (String.valueOf(tv.getId()).equals(electronicId)) {
                        electronic = tv;
                        break;
                    }
                }
                seller = findSeller(electronic);
                paymentValidation(buyer, seller, electronic);
                makeTransaction(FilePaths.TV_PATH, buyer.getId(), seller.getId(), electronic.getId(), electronic.getPrice());
                break;

        }
    }

    private static void paymentValidation(User buyer, User seller, Electronics electronicsToBuy) throws Exception {
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

    private static void makeTransaction(String electronicsPath, UUID buyerId, UUID sellerId, UUID notebookId, int notebookPrice) throws IOException {
        FileService.changeBalance(FilePaths.USERS_PATH, buyerId, -notebookPrice);
        FileService.changeBalance(FilePaths.USERS_PATH, sellerId, notebookPrice);
        FileService.removeLine(electronicsPath, String.valueOf(notebookId));
        FileService.changeUserId(FilePaths.USER_PRODUCT_PATH, String.valueOf(buyerId), String.valueOf(notebookId));
    }

    private static User findSeller(Electronics electronics) throws IOException {
        User seller = new User();
        List<String> users = FileService.read(FilePaths.USER_PRODUCT_PATH);
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
