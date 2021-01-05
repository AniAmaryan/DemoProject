package com.company.services;


import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.Tablet;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TabletService extends ElectronicsService implements CameraManager {
    private Tablet tablet;

    public Tablet[] readTabletData() throws Exception, IntException {

        List<String> read = FileService.read(FilePaths.TABLET_PATH);
        Tablet[] tablets = new Tablet[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] notebookArray = read.get(i).split(",");
            tablets[i] = new Tablet();
            tablets[i].setManufacturer(notebookArray[0]);
            tablets[i].setModel(notebookArray[1]);
            tablets[i].setPrice(Integer.parseInt(notebookArray[2]));
            tablets[i].setUnderWarranty(notebookArray[3].equals("Yes"));
            tablets[i].setScreenSize(Double.parseDouble(notebookArray[4]));
            tablets[i].setHasCamera(notebookArray[5].equals("Yes"));
            tablets[i].setCameraResolution(Double.parseDouble(notebookArray[6]));
        }
        return tablets;
    }

    @Override
    public void createCameraCritters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Has camera : Y for (Yes) or N for (NO)");
        char answer = scanner.next().charAt(0);
        tablet.setHasCamera(answer == 'y' || answer == 'Y');
        System.out.println("Enter camera resolution");
        tablet.setCameraResolution(scanner.nextDouble());
    }

    public void writeAllData(){
        FileService.writeData(FilePaths.TABLET_PATH, toString());
    }

    @Override
    public String toString() {
        final String camera = (tablet.isHasCamera() ? "Yes" : "No");
        final String resolution = "," + tablet.getCameraResolution();
        return super.toString() + camera + resolution;
    }

    public UUID createTablet() throws StringException, IntException {
        tablet = new Tablet();
        tablet = (Tablet) createBasicCritters(tablet);
        createCameraCritters();
        System.out.println("Tablet created !!!\n");
        return tablet.getId();
    }
}
