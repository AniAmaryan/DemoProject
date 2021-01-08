package com.company.services;

import com.company.exceptions.IntException;
import com.company.exceptions.StringException;
import com.company.model.FilePaths;
import com.company.model.PC;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * This is PC's service
 *
 * @author Ani Amaryan
 */
public class PCService extends ElectronicsService {

    /**
     * This method read pc's data from PC_PATH and stored it in pc object
     *
     * @return
     */
    public PC[] readPCData() {

        List<String> read = null;
        try {
            read = FileService.read(FilePaths.PC_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PC[] pc = new PC[read.size()];
        for (int i = 0; i < read.size(); i++) {
            String[] pcArray = read.get(i).split(",");
            pc[i] = new PC();
            pc[i].setId(UUID.fromString(pcArray[0]));
            try {
                pc[i].setManufacturer(pcArray[1]);
            } catch (StringException e) {
                e.printStackTrace();
            }
            pc[i].setModel(pcArray[2]);
            try {
                pc[i].setPrice(Integer.parseInt(pcArray[3]));
            } catch (IntException e) {
                e.printStackTrace();
            }
            pc[i].setUnderWarranty(pcArray[4].equals("Yes"));
            pc[i].setScreenSize(Double.parseDouble(pcArray[5]));
            pc[i].setProductStatus(pcArray[6]);
        }
        return pc;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    /**
     * This method create pc and returns pc UUID.
     *
     * @return pc's UUID
     */
    public UUID createPc() {
        PC pc = new PC();
        pc = (PC) createBasicCritters(pc);
        System.out.println("PC created !!!\n");
        return pc.getId();
    }

    /**
     * This method writes all pc's data in PC_PATH.
     */
    public void writeAllData() {
        FileService.writeData(FilePaths.PC_PATH, super.toString());
    }
}
