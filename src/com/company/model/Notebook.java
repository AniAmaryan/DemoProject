package com.company.model;

/**
 * This is Notebook's model class
 *
 * @author Ani Amaryan
 */
public class Notebook extends Electronics {
    private boolean hasCamera;
    private double cameraResolution;

    /**
     * This method return information about notebook.
     *
     * @return notebook data.
     */
    @Override
    public String printElectronicData() {
        return super.printElectronicData() + "Camera = '" + isHasCamera() + '\'' + "\n" +
                "Camera Resolution = '" + getCameraResolution() + '\'' + "\n";
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
