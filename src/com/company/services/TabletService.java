package com.company.services;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.Tablet;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This is Tablet's service
 *
 * @author Ani Amaryan
 */
public class TabletService extends ElectronicsService implements CameraManager {
    private Tablet tablet;

    /**
     * This method read tablet's data from TABLET_PATH and stored it in tablet object
     *
     * @return
     */
    public Tablet[] readTabletData() {

        List<String> read = null;
        try {
            read = FileService.read(FilePaths.TABLET_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tablet[] tablets = new Tablet[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] tabletArray = read.get(i).split(",");
            tablets[i] = new Tablet();
            tablets[i].setId(UUID.fromString(tabletArray[0]));
            try {
                tablets[i].setManufacturer(tabletArray[1]);
            } catch (StringException e) {
                e.printStackTrace();
            }
            tablets[i].setModel(tabletArray[2]);
            try {
                tablets[i].setPrice(Integer.parseInt(tabletArray[3]));
            } catch (IntException e) {
                e.printStackTrace();
            }
            tablets[i].setUnderWarranty(tabletArray[4].equals("Yes"));
            tablets[i].setScreenSize(Double.parseDouble(tabletArray[5]));
            tablets[i].setProductStatus(tabletArray[6]);
            tablets[i].setHasCamera(tabletArray[7].equals("Yes"));
            tablets[i].setCameraResolution(Double.parseDouble(tabletArray[8]));
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

    @Override
    public String toString() {
        final String camera = "," + (tablet.isHasCamera() ? "Yes" : "No");
        final String resolution = "," + tablet.getCameraResolution();
        return super.toString() + camera + resolution;
    }

    /**
     * This method create tablet and returns tablet UUID.
     *
     * @return tablet's UUID
     */
    public UUID createTablet() {
        tablet = new Tablet();
        tablet = (Tablet) createBasicCritters(tablet);
        createCameraCritters();
        System.out.println("Tablet created !!!\n");
        return tablet.getId();
    }

    /**
     * This method writes all tablet's data in TABLET_PATH.
     */
    public void writeAllData() {
        FileService.writeData(FilePaths.TABLET_PATH, toString());
    }

}
