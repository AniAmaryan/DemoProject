package com.company.services;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.TV;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This is TV's service
 *
 * @author Ani Amaryan
 */
public class TVService extends ElectronicsService {
    private TV tv;


    /**
     * This method read tv's data from TV_PATH and stored it in tv object
     *
     * @return
     */
    public TV[] readTVData() {

        List<String> read = null;
        try {
            read = FileService.read(FilePaths.TV_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TV[] tvs = new TV[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] tvArray = read.get(i).split(",");
            tvs[i] = new TV();
            tvs[i].setId(UUID.fromString(tvArray[0]));
            try {
                tvs[i].setManufacturer(tvArray[1]);
            } catch (StringException e) {
                e.printStackTrace();
            }
            tvs[i].setModel(tvArray[2]);
            try {
                tvs[i].setPrice(Integer.parseInt(tvArray[3]));
            } catch (IntException e) {
                e.printStackTrace();
            }
            tvs[i].setUnderWarranty(tvArray[4].equals("Yes"));
            tvs[i].setScreenSize(Double.parseDouble(tvArray[5]));
            tvs[i].setProductStatus(tvArray[6]);
            tvs[i].setColorTV(tvArray[7].equals("Yes"));
            tvs[i].setFullHD(tvArray[8].equals("Yes"));
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

    @Override
    public String toString() {
        final String color = "," + (tv.isColorTV() ? "Yes" : "No");
        final String fullHD = "," + (tv.isFullHD() ? "Yes" : "No");
        return super.toString() + color + fullHD;
    }

    /**
     * This method create tv and returns tvs UUID.
     *
     * @return tv's UUID
     */
    public UUID createTV() {
        tv = new TV();
        tv = (TV) createBasicCritters(tv);
        createTVCritters();
        System.out.println("TV created !!!\n");
        return tv.getId();
    }

    /**
     * This method writes all Tv's data in TV_PATH.
     */
    public void writeAllData() {
        FileService.writeData(FilePaths.TV_PATH, toString());
    }

}