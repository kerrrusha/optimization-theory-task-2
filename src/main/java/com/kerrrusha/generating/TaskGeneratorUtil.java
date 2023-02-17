package com.kerrrusha.generating;

import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.Task;

import java.util.*;

import static com.kerrrusha.Util.getElementEntriesCount;
import static com.kerrrusha.Util.getRandomInt;
import static java.util.stream.Collectors.toList;

public class TaskGeneratorUtil {

    private static final int MAX_ALLOWED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT = 1;

    private static final int N_FROM_INCLUSIVE = 5;
    private static final int N_TO_EXCLUSIVE = 8;

    private static final int M_DEFAULT_VALUE = 1;

    private static final int T_FROM_INCLUSIVE = 5;
    private static final int T_TO_INCLUSIVE = 21;

    private static final int D_FROM_INCLUSIVE = 20;
    private static final int D_TO_EXCLUSIVE = 51;

    private static final int U_FROM_INCLUSIVE = 1;
    private static final int U_TO_INCLUSIVE = 4;

    public static Task generateSingleTask() {
        int n = getRandomInt(N_FROM_INCLUSIVE, N_TO_EXCLUSIVE);
        int m = M_DEFAULT_VALUE;
        List<ScheduleElement> schedule = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int t = getRandomInt(T_FROM_INCLUSIVE, T_TO_INCLUSIVE);
            if (getTimeValueDuplicateGroupsAmount(schedule, t) > MAX_ALLOWED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT) {
                i -= 1;
                continue;
            }
            int d = getRandomInt(D_FROM_INCLUSIVE, D_TO_EXCLUSIVE);
            int u = getRandomInt(U_FROM_INCLUSIVE, U_TO_INCLUSIVE);
            schedule.add(new ScheduleElement(i, t, d, u));
        }
        return new Task(n, m, schedule);
    }

    private static int getTimeValueDuplicateGroupsAmount(List<ScheduleElement> schedule, int newT) {
        List<Integer> timeValues = schedule
                .stream()
                .map(ScheduleElement::getT)
                .collect(toList());
        timeValues.add(newT);
        Set<Integer> distinctTimeValues = new HashSet<>(timeValues);
        return (int) distinctTimeValues
                .stream()
                .filter(t -> getElementEntriesCount(timeValues, t) > 1)
                .count();
    }

    public static List<Task> generateAmountOfTasks(int amountOfTasksToBeGenerated) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i <= amountOfTasksToBeGenerated; i++) {
            tasks.add(generateSingleTask());
        }
        return tasks;
    }

}
