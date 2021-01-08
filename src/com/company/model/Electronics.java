package com.company.model;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;

import java.util.UUID;

/**
 * This is Electronics's model abstract class
 *
 * @author Ani Amaryan
 */
public abstract class Electronics {
    private UUID id;
    private String manufacturer;
    private String model;
    private int price;
    private boolean isUnderWarranty;
    private double screenSize;
    private String productStatus;

    /**
     * This method return information about electronic.
     *
     * @return electronic data.
     */
    public String printElectronicData() {
        return "Id = '" + id + '\'' + "\n" +
                "Manufacturer = '" + manufacturer + '\'' + "\n" +
                "Model = '" + model + '\'' + "\n" +
                "Price = '" + price + '\'' + "\n" +
                "IsUnderWarranty = " + (isUnderWarranty ? "Yes" : "No") + "\n" +
                "ScreenSize = '" + screenSize + '\'' + "\n" +
                "Status = '" + productStatus + '\'' + "\n";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * This is manufacturer's setter method and in this method checks if
     * the manufacturer's name is empty or matches regex.
     *
     * @param manufacturer
     * @throws StringException
     */
    public void setManufacturer(String manufacturer) throws StringException {
        String regex = "[A-Za-z !,?._'@]+";
        if (!manufacturer.isEmpty() && manufacturer.matches(regex)) {
            this.manufacturer = manufacturer;
        } else {
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

    /**
     * This is price's setter method and in this method checks if the price not negative
     *
     * @param price
     * @throws IntException
     */
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

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}

