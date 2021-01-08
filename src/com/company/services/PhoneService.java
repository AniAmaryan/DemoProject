package com.company.services;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.Phone;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This is Phone's service
 *
 * @author Ani Amaryan
 */
public class PhoneService extends ElectronicsService implements CameraManager {
    private Phone phone;

    /**
     * This method read phone's data from PHONE_PATH and stored it in phone object
     *
     * @return
     */
    public Phone[] readPhoneData() {

        List<String> read = null;
        try {
            read = FileService.read(FilePaths.PHONE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Phone[] phones = new Phone[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] phoneArray = read.get(i).split(",");
            phones[i] = new Phone();
            phones[i].setId(UUID.fromString(phoneArray[0]));
            try {
                phones[i].setManufacturer(phoneArray[1]);
            } catch (StringException e) {
                e.printStackTrace();
            }
            phones[i].setModel(phoneArray[2]);
            try {
                phones[i].setPrice(Integer.parseInt(phoneArray[3]));
            } catch (IntException e) {
                e.printStackTrace();
            }
            phones[i].setUnderWarranty(phoneArray[4].equals("Yes"));
            phones[i].setScreenSize(Double.parseDouble(phoneArray[5]));
            phones[i].setProductStatus(phoneArray[6]);
            phones[i].setHasCamera(phoneArray[7].equals("Yes"));
            phones[i].setCameraResolution(Double.parseDouble(phoneArray[8]));
        }
        return phones;
    }

    @Override
    public void createCameraCritters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Has camera : Y for (Yes) or N for (NO)");
        char answer = scanner.next().charAt(0);
        phone.setHasCamera(answer == 'y' || answer == 'Y');
        System.out.println("Enter camera resolution");
        phone.setCameraResolution(scanner.nextDouble());
    }

    @Override
    public String toString() {
        final String camera = "," + (phone.isHasCamera() ? "Yes" : "No");
        final String resolution = "," + phone.getCameraResolution();
        return super.toString() + camera + resolution;
    }

    /**
     * This method create phone and returns phone UUID.
     *
     * @return phone's UUID
     */
    public UUID createPhone() {
        phone = new Phone();
        phone = (Phone) createBasicCritters(phone);
        createCameraCritters();
        System.out.println("Phone created !!!\n");
        return phone.getId();
    }

    /**
     * This method writes all phone's data in PHONE_PATH.
     */
    public void writeAllData() {
        FileService.writeData(FilePaths.PHONE_PATH, toString());
    }

}
