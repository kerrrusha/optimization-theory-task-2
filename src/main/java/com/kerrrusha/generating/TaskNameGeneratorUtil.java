package com.kerrrusha.generating;

import java.time.LocalDateTime;

public class TaskNameGeneratorUtil {

    private static final String FILENAME_TEMPLATE = "тест-2-варіант-питання-%s";

    public static String generateTaskName() {
        return String.format(FILENAME_TEMPLATE, LocalDateTime.now());
    }

}
