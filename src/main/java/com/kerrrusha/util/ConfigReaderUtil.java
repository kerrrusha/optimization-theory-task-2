package com.kerrrusha.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfigReaderUtil {

    private static final String FILENAME = "config.txt";
    private static final char COMMENT_SYMBOL = '#';
    private static final String EQUAL_SYMBOL = "=";

    public static Map<String, String> read() {
        return readConfigFromFile();
    }

    public static Map<String, String> readConfigFromFile() {
        Map<String, String> config = new HashMap<>();
        try {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains(EQUAL_SYMBOL) || line.trim().charAt(0) == COMMENT_SYMBOL) {
                    continue;
                }

                String[] parts = line.split(EQUAL_SYMBOL);
                if (parts.length == 2) {
                    config.put(parts[0], parts[1]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILENAME);
        }
        return config;
    }

}
