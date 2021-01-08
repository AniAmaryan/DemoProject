package com.company.services;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.Electronics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.UUID;

/**
 * This is electronics's service abstract class
 *
 * @author Ani Amaryan
 */
public abstract class ElectronicsService {
    private Electronics electronics;

    /**
     * This method create electronic object using  and return it.
     *
     * @param electronics
     * @return
     */
    public Electronics createBasicCritters(Electronics electronics) {
        electronics.setId(UUID.randomUUID());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter manufacturer");
        try {
            electronics.setManufacturer(scanner.next());
        } catch (StringException e) {
            e.printStackTrace();
        }
        System.out.println("Enter model");
        electronics.setModel(scanner.next());
        System.out.println("Enter price");
        try {
            electronics.setPrice(scanner.nextInt());
        } catch (IntException e) {
            e.printStackTrace();
        }
        System.out.println("Enter is the device under warranty: Y for (Yes) or N for (NO)");
        char answer = scanner.next().charAt(0);
        electronics.setUnderWarranty((answer == 'y' || answer == 'Y'));
        System.out.println("Enter screenSize");
        electronics.setScreenSize(scanner.nextDouble());
        this.electronics = electronics;
        electronics.setProductStatus("TO_SELL");
        return electronics;
    }

    @Override
    public String toString() {
        return electronics.getId() + "," + electronics.getManufacturer() + ',' + electronics.getModel() + ","
                + electronics.getPrice() + "," + (electronics.isUnderWarranty() ? "Yes" : "No") + ","
                + electronics.getScreenSize() + "," + electronics.getProductStatus();
    }

    /**
     * Comparator for price
     */
    private final Comparator<Electronics> priceComparator = (o1, o2) -> o1.getPrice() - o2.getPrice();

    /**
     * Sorted electronic by price using priceComparator.
     *
     * @param electronics
     */
    public void sortByPrice(Electronics[] electronics) {
        System.out.println("----------------------");
        System.out.println("Sorting By Price: ");
        System.out.println("----------------------");

        Arrays.sort(electronics, priceComparator);
        for (Electronics electronic : electronics) {
            if (!electronic.getProductStatus().equalsIgnoreCase("SOLD")) {
                System.out.println(electronic.printElectronicData());
            }
        }
    }
}
