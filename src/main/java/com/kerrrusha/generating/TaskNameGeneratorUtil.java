package com.kerrrusha.generating;

import com.kerrrusha.model.ScheduleType;
import com.kerrrusha.model.Task;

public class TaskNameGeneratorUtil {

    private static final String FILENAME_TEMPLATE = "Т2-%s-%s-t";

    public static String generateTaskName(Task task) {
        return String.format(FILENAME_TEMPLATE, task.getId(), decideMinOrMax(task.getTaskCondition().getScheduleType()));
    }

    private static String decideMinOrMax(ScheduleType scheduleType) {
        return scheduleType.equals(ScheduleType.MIN_SUM_ENDING_TIME)
                ? "min"
                : "max";
    }

}
