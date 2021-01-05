package com.company.model;


import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import java.util.Scanner;
import java.util.UUID;

public abstract class Electronics {
    private UUID id;
    private String manufacturer;
    private String model;
    private int price;
    private boolean isUnderWarranty;
    private double screenSize;

    public void sortByPrice(Electronics[] electronics) throws IntException {
        System.out.println("----------------------");
        System.out.println("Sorting By Price: ");
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
        for (Electronics el : electronics) {
            System.out.println(el.printElectronicData());
        }
    }

    public void createBasicCritters() throws StringException, IntException {
        setId(UUID.randomUUID());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter manufacturer");
        setManufacturer(scanner.next());
        System.out.println("Enter model");
        setModel(scanner.next());
        System.out.println("Enter price");
        setPrice(scanner.nextInt());
        System.out.println("Enter is the device under warranty: Y for (Yes) or N for (NO)");
        char answer = scanner.next().charAt(0);
        setUnderWarranty((answer == 'y' || answer == 'Y'));
        System.out.println("Enter screenSize");
        setScreenSize(scanner.nextDouble());
    }

    public String printElectronicData() {
        return "Manufacturer = '" + manufacturer + '\'' + "\n" +
                "Model = '" + model + '\'' + "\n" +
                "Price = " + price + "\n" +
                "IsUnderWarranty = " + (isUnderWarranty ? "Yes" : "No") + "\n" +
                "ScreenSize = " + screenSize + "\n";
    }

    @Override
    public String toString() {
        return id + "," + manufacturer + "," + model + "," + price + ","
                + (isUnderWarranty ? "Yes" : "No") + "," + screenSize + ",";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) throws StringException {
        String regex = "[A-Za-z !,?._'@]+";
        if (!manufacturer.isEmpty() && manufacturer.matches(regex)) {
            this.manufacturer = manufacturer;
        } else {
            System.out.println("Invalid option");
            throw new StringException("Invalid option: " + manufacturer);
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws IntException {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IntException("The price shouldn't be zero or less then zero ");
        }
    }

    public boolean isUnderWarranty() {
        return isUnderWarranty;
    }

    public void setUnderWarranty(boolean underWarranty) {
        isUnderWarranty = underWarranty;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}

