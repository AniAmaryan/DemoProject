package com.company.services;


import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.Electronics;
import java.util.Scanner;
import java.util.UUID;

public abstract class ElectronicsService {
    private Electronics electronics;

    public Electronics createBasicCritters(Electronics electronics) throws StringException, IntException {
        electronics.setId(UUID.randomUUID());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter manufacturer");
        electronics.setManufacturer(scanner.next());
        System.out.println("Enter model");
        electronics.setModel(scanner.next());
        System.out.println("Enter price");
        electronics.setPrice(scanner.nextInt());
        System.out.println("Enter is the device under warranty: Y for (Yes) or N for (NO)");
        char answer = scanner.next().charAt(0);
        electronics.setUnderWarranty((answer == 'y' || answer == 'Y'));
        System.out.println("Enter screenSize");
        electronics.setScreenSize(scanner.nextDouble());
        this.electronics = electronics;
        return electronics;
    }

    @Override
    public String toString() {
        return electronics.getId() + "," + electronics.getManufacturer() + ',' + electronics.getModel() + "," + electronics.getPrice() + ","
                + (electronics.isUnderWarranty() ? "Yes" : "No") + "," + electronics.getScreenSize() + ",";
    }

    public void sortByPrice(Electronics[] electronics) {
        System.out.println("----------------------");
        System.out.println("Sorting By Price: ");
        System.out.println("----------------------");
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < electronics.length; i++) {
                if (electronics[i - 1].getPrice() > electronics[i].getPrice()) {
                    Electronics temp = electronics[i];
                    electronics[i] = electronics[i - 1];
                    electronics[i - 1] = temp;
                    swapped = true;
                }
            }
        }
        for (Electronics electro : electronics) {
            System.out.println(electro.printElectronicData());
        }
    }
}
