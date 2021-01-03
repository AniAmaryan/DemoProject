package com.company.services;

import com.company.model.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * This class works with files
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
     * This method writes the text specified in the parameters to the path
     *
     * @param path
     * @param text
     * @throws IOException
     */
    public static void write(String path, String text) throws IOException {
        Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
    }

    public static void changeBalance(User user, int newBalance) throws IOException {
        final String path = "C:\\Users\\User\\IdeaProjects\\armBay\\src\\com\\company\\users.txt";
        Path pathForOverwrite = Paths.get(path);
        Charset charset = StandardCharsets.UTF_8;

        List<String> read = FileService.read(path);
        StringBuilder changedString = new StringBuilder();
        for (String s : read) {
            String[] line = s.split(",");
            if (String.valueOf(user.getId()).equals(line[0])) {
                line[5] = String.valueOf(Integer.parseInt(line[5]) + newBalance);
                user.setBalance(user.getBalance() + newBalance);
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
        Files.writeString(pathForOverwrite, result, charset);
    }
}
