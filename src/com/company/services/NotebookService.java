package com.company.services;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.Notebook;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This is Notebook's service
 *
 * @author Ani Amaryan
 */
public class NotebookService extends ElectronicsService implements CameraManager {
    private Notebook notebook;

    /**
     * This method read notebook's data from NOTEBOOK_PATH and stored it in notebook object
     *
     * @return
     */
    public Notebook[] readNotebookData() {

        List<String> read = null;
        try {
            read = FileService.read(FilePaths.NOTEBOOK_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Notebook[] notebooks = new Notebook[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] notebookArray = read.get(i).split(",");
            notebooks[i] = new Notebook();
            notebooks[i].setId(UUID.fromString(notebookArray[0]));
            try {
                notebooks[i].setManufacturer(notebookArray[1]);
            } catch (StringException e) {
                e.printStackTrace();
            }
            notebooks[i].setModel(notebookArray[2]);
            try {
                notebooks[i].setPrice(Integer.parseInt(notebookArray[3]));
            } catch (IntException e) {
                e.printStackTrace();
            }
            notebooks[i].setUnderWarranty(notebookArray[4].equals("Yes"));
            notebooks[i].setScreenSize(Double.parseDouble(notebookArray[5]));
            notebooks[i].setProductStatus(notebookArray[6]);
            notebooks[i].setHasCamera(notebookArray[7].equals("Yes"));
            notebooks[i].setCameraResolution(Double.parseDouble(notebookArray[8]));
        }
        return notebooks;
    }

    /**
     * This method create notebook and returns notebooks UUID.
     *
     * @return notebook's UUID
     */
    public UUID createNotebook() {
        notebook = new Notebook();
        notebook = (Notebook) createBasicCritters(notebook);
        createCameraCritters();
        System.out.println("Notebook created !!!\n");
        return notebook.getId();
    }

    @Override
    public void createCameraCritters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Has camera : Y for (Yes) or N for (NO)");
        char answer = scanner.next().charAt(0);
        notebook.setHasCamera(answer == 'y' || answer == 'Y');
        System.out.println("Enter camera resolution");
        notebook.setCameraResolution(scanner.nextDouble());
    }

    @Override
    public String toString() {
        final String camera = "," + (notebook.isHasCamera() ? "Yes" : "No");
        final String resolution = "," + notebook.getCameraResolution();
        return super.toString() + camera + resolution;
    }

    /**
     * This method writes all notebook's data in NOTEBOOK_PATH.
     */
    public void writeAllData() {
        FileService.writeData(FilePaths.NOTEBOOK_PATH, toString());
    }
}
