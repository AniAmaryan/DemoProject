package com.company.services;


import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.Phone;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PhoneService extends ElectronicsService implements CameraManager {
    private Phone phone;

    public PhoneService() {
    }

    public Phone[] readPhoneData() throws Exception {

        List<String> read = FileService.read(FilePaths.PHONE_PATH);
        Phone[] phones = new Phone[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] notebookArray = read.get(i).split(",");
            phones[i] = new Phone();
            phones[i].setManufacturer(notebookArray[0]);
            phones[i].setModel(notebookArray[1]);
            phones[i].setPrice(Integer.parseInt(notebookArray[2]));
            phones[i].setUnderWarranty(notebookArray[3].equals("Yes"));
            phones[i].setScreenSize(Double.parseDouble(notebookArray[4]));
            phones[i].setHasCamera(notebookArray[5].equals("Yes"));
            phones[i].setCameraResolution(Double.parseDouble(notebookArray[6]));
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

    public void writeAllData(){
        FileService.writeData(FilePaths.PHONE_PATH, toString());
    }

    @Override
    public String toString() {
        final String camera = (phone.isHasCamera() ? "Yes" : "No");
        final String resolution = "," + phone.getCameraResolution();
        return super.toString() + camera + resolution;
    }

    public UUID createPhone() throws Exception {
        phone = new Phone();
        phone = (Phone) createBasicCritters(phone);
        createCameraCritters();
        System.out.println("Phone created !!!\n");
        return phone.getId();
    }
}
