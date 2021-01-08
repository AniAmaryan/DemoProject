package com.company.model;

/**
 * This is TV's model class
 *
 * @author Ani Amaryan
 */
public class TV extends Electronics {
    private boolean isColorTV;
    private boolean isFullHD;

    /**
     * This method return information about TV.
     *
     * @return tv data.
     */
    @Override
    public String printElectronicData() {
        return super.printElectronicData() + "Color = '" + isColorTV() + '\'' + "\n" +
                "Full HD = " + isFullHD() + "\n";
    }

    public boolean isColorTV() {
        return isColorTV;
    }

    public void setColorTV(boolean colorTV) {
        isColorTV = colorTV;
    }

    public boolean isFullHD() {
        return isFullHD;
    }

    public void setFullHD(boolean fullHD) {
        isFullHD = fullHD;
    }
}

