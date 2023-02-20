package com.kerrrusha.model;

import static com.kerrrusha.util.TaskUtil.getRandomInt;

public enum ScheduleType {

    MAX_SUM_ENDING_TIME(
            "максимальним сумарним часом закінчення",
                    "максимальним сумарним очікуванням",
                    "максимальним сумарним часовим зміщення",
                    "максимальною сумарною тривалістю проходження"
            ),
    MIN_SUM_ENDING_TIME(
            "мінімальним сумарним часом закінчення",
            "мінімальним сумарним очікуванням",
            "мінімальним сумарним часовим зміщенням",
            "мінімальною сумарною тривалістю проходження"
    );

    private final String[] printableNames;

    ScheduleType(String... names) {
        printableNames = names;
    }

    public String getPrintableName() {
        int randNameIndex = getRandomInt(0, printableNames.length);
        return printableNames[randNameIndex];
    }

    @Override
    public String toString() {
        return "ScheduleType{" +
                "printableName='" + getPrintableName() + '\'' +
                '}';
    }
}
