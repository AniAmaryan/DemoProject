package com.company.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This class works with files.
 *
 * @author Ani Amaryan
 */
public class FileService {

    /**
     * This method reads all lines in path and returns list of strings
     *
     * @param path
     * @return list of strings
     * @throws IOException
     */
    public static List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    /**
     * This method changes old balance to new balance and writes changed balance in txt file.
     *
     * @param path
     * @param userId
     * @param newBalance
     * @throws IOException
     */
    public static void changeBalance(String path, UUID userId, int newBalance) {

        Path pathForOverwrite = Paths.get(path);
        Charset charset = StandardCharsets.UTF_8;

        List<String> read = null;
        try {
            read = FileService.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder changedString = new StringBuilder();
        for (String s : read) {
            String[] line = s.split(",");
            if (String.valueOf(userId).equals(line[0])) {
                line[5] = String.valueOf(Integer.parseInt(line[5]) + newBalance);
            }
            for (int j = 0; j < line.length; j++) {
                changedString.append(line[j]);
                if (j != line.length - 1) {
                    changedString.append(",");
                } else {
                    changedString.append("\n");
                }
            }
        }
        String result = changedString.toString();
        try {
            Files.writeString(pathForOverwrite, result, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes data in txt file, using path and String data.
     *
     * @param path
     * @param data
     */
    public static void writeData(String path, String data) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(data);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    /**
     * This method changes product status from TO_SELL to SOLD and writes in txt file.
     *
     * @param path
     * @param electronicId
     */
    public static void changeProductStatus(String path, String electronicId) {
        Path pathForOverwrite = Paths.get(path);
        Charset charset = StandardCharsets.UTF_8;

        List<String> read = null;
        try {
            read = read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder changedString = new StringBuilder();
        for (String s : read) {
            String[] line = s.split(",");
            if ((line[0].equals(electronicId))) {
                line[6] = "SOLD";
            }
            for (int j = 0; j < line.length; j++) {
                changedString.append(line[j]);
                if (j != line.length - 1) {
                    changedString.append(",");
                } else {
                    changedString.append("\n");
                }
            }
        }
        String result = changedString.toString();

        try {
            Files.writeString(pathForOverwrite, result, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method changes User id and writes in txt file.
     *
     * @param path
     * @param userId
     * @param soldProductId
     */
    public static void changeUserId(String path, String userId, String soldProductId) {
        Path pathForOverwrite = Paths.get(path);
        Charset charset = StandardCharsets.UTF_8;

        List<String> read = null;
        try {
            read = read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder changedString = new StringBuilder();
        for (String s : read) {
            String[] line = s.split(",");
            if ((line[1].equals(soldProductId))) {
                line[0] = userId;
                line[2] = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
                line[3] = "SOLD";
            }
            for (int j = 0; j < line.length; j++) {
                changedString.append(line[j]);
                if (j != line.length - 1) {
                    changedString.append(",");
                } else {
                    changedString.append("\n");
                }
            }
        }
        String result = changedString.toString();
        try {
            Files.writeString(pathForOverwrite, result, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
