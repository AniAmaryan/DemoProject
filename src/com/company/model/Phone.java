package com.company.model;

/**
 * This is Phone's model class
 *
 * @author Ani Amaryan
 */
public class Phone extends Electronics {
    private boolean hasCamera;
    private double cameraResolution;

    /**
     * This method return information about phone.
     *
     * @return phone data.
     */
    @Override
    public String printElectronicData() {
        return super.printElectronicData() + "Camera = '" + isHasCamera() + '\'' + "\n" +
                "Camera Resolution = " + getCameraResolution() + "\n";
    }

    public boolean isHasCamera() {
        return hasCamera;
    }

    public void setHasCamera(boolean hasCamera) {
        this.hasCamera = hasCamera;
    }

    public double getCameraResolution() {
        return cameraResolution;
    }

    public void setCameraResolution(double cameraResolution) {
        this.cameraResolution = cameraResolution;
    }
}

