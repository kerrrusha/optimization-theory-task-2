package com.kerrrusha.generating;

import com.kerrrusha.model.Task;

public class MoodleXmlFilenameGeneratorUtil {

    private static final String FILENAME_TEMPLATE = "%s.xml";

    public static String generateFilename(Task task) {
        return String.format(FILENAME_TEMPLATE, TaskNameGeneratorUtil.generateTaskName(task)).replaceAll(":", ".");
    }

}
