package com.company.services;

import com.company.model.FilePaths;
import com.company.model.PC;
import java.util.List;
import java.util.UUID;

public class PCService extends ElectronicsService {

    public PC[] readPCData() throws Exception {

        List<String> read = FileService.read(FilePaths.PC_PATH);
        PC[] pc = new PC[read.size()];
        for (int i = 0; i < read.size(); i++) {
            String[] pcArray = read.get(i).split(",");
            pc[i] = new PC();
            pc[i].setManufacturer(pcArray[0]);
            pc[i].setModel(pcArray[1]);
            pc[i].setPrice(Integer.parseInt(pcArray[2]));
            pc[i].setUnderWarranty(pcArray[3].equals("Yes"));
            pc[i].setScreenSize(Double.parseDouble(pcArray[4]));
        }
        return pc;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public PCService() {
    }

    public UUID createPc() throws Exception {
        PC pc = new PC();
        pc = (PC) createBasicCritters(pc);
        System.out.println("PC created !!!\n");
        return pc.getId();
    }

    public void writeAllData() {
        FileService.writeData(FilePaths.PC_PATH, super.toString());
    }
}
