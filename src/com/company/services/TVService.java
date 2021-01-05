package com.company.services;


import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.TV;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TVService extends ElectronicsService {
    private TV tv;

    public TVService() {
    }

    public TV[] readTVData() throws Exception, IntException {

        List<String> read = FileService.read(FilePaths.TV_PATH);
        TV[] tvs = new TV[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] notebookArray = read.get(i).split(",");
            tvs[i] = new TV();
            tvs[i].setManufacturer(notebookArray[0]);
            tvs[i].setModel(notebookArray[1]);
            tvs[i].setPrice(Integer.parseInt(notebookArray[2]));
            tvs[i].setUnderWarranty(notebookArray[3].equals("Yes"));
            tvs[i].setScreenSize(Double.parseDouble(notebookArray[4]));
            tvs[i].setColorTV(notebookArray[5].equals("Yes"));
            tvs[i].setFullHD(notebookArray[6].equals("Yes"));
        }
        return tvs;
    }

    public void createTVCritters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is TV Colorful : Y for (Yes) or N for (NO)");
        char answerForColor = scanner.next().charAt(0);
        tv.setColorTV(answerForColor == 'y' || answerForColor == 'Y');
        System.out.println("Is TV Full HD : Y for (Yes) or N for (NO)");
        char answerForHD = scanner.next().charAt(0);
        tv.setFullHD(answerForHD == 'y' || answerForHD == 'Y');
    }

    public void writeAllData() {
        FileService.writeData(FilePaths.TV_PATH, toString());
    }

    @Override
    public String toString() {
        final String color = (tv.isColorTV() ? "Yes" : "No");
        final String fullHD = "," + (tv.isFullHD() ? "Yes" : "No");
        return super.toString() + color + fullHD;
    }

    public UUID createTV() throws StringException, IntException {
        tv = new TV();
        tv = (TV) createBasicCritters(tv);
        createTVCritters();
        System.out.println("TV created !!!\n");
        return tv.getId();
    }
}
