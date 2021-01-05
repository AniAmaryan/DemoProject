package com.company.services;


import com.company.exceptions.IntException;
import com.company.model.FilePaths;
import com.company.model.Notebook;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class NotebookService extends ElectronicsService implements CameraManager {
    private Notebook notebook;

    public Notebook[] readNotebookData() throws Exception, IntException {

        List<String> read = FileService.read(FilePaths.NOTEBOOK_PATH);
        Notebook[] notebooks = new Notebook[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] notebookArray = read.get(i).split(",");
            notebooks[i] = new Notebook();
            notebooks[i].setId(UUID.fromString(notebookArray[0]));
            notebooks[i].setManufacturer(notebookArray[1]);
            notebooks[i].setModel(notebookArray[2]);
            notebooks[i].setPrice(Integer.parseInt(notebookArray[3]));
            notebooks[i].setUnderWarranty(notebookArray[4].equals("Yes"));
            notebooks[i].setScreenSize(Double.parseDouble(notebookArray[5]));
            notebooks[i].setHasCamera(notebookArray[6].equals("Yes"));
            notebooks[i].setCameraResolution(Double.parseDouble(notebookArray[7]));
        }
        return notebooks;
    }

    public NotebookService() {
    }

    public UUID createNotebook() throws Exception {
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
        final String camera = (notebook.isHasCamera() ? "Yes" : "No");
        final String resolution = "," + notebook.getCameraResolution();
        return super.toString() + camera + resolution;
    }

    public void writeAllData() {
        FileService.writeData(FilePaths.NOTEBOOK_PATH, toString());
    }
}
