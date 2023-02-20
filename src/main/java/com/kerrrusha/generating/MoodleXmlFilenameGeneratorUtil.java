package com.kerrrusha.generating;

public class MoodleXmlFilenameGeneratorUtil {

    private static final String FILENAME_TEMPLATE = "%s.xml";

    public static String generateFilename() {
        return String.format(FILENAME_TEMPLATE, TaskNameGeneratorUtil.generateTaskName()).replaceAll(":", ".");
    }

}
