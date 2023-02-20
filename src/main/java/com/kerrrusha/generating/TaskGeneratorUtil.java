package com.kerrrusha.generating;

import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.ScheduleType;
import com.kerrrusha.model.Task;
import com.kerrrusha.model.TaskCondition;

import java.util.*;

import static com.kerrrusha.util.TaskUtil.getElementEntriesCount;
import static com.kerrrusha.util.TaskUtil.getRandomInt;
import static java.util.stream.Collectors.toList;

public class TaskGeneratorUtil {

    private static final int EXPECTED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT = 1;

    private static final int AMOUNT_OF_POSSIBLE_ALT_ANSWER_COUNTS = 6;
    private static final int ALT_ANSWER_COUNT_FROM_INCLUSIVE = 1;
    private static final int ALT_ANSWER_COUNT_TO_EXCLUSIVE = 25;

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
        final int n = getRandomInt(N_FROM_INCLUSIVE, N_TO_EXCLUSIVE);
        final int m = M_DEFAULT_VALUE;

        ScheduleType scheduleType = getRandomScheduleType();
        List<ScheduleElement> schedule = generateSchedule(n);
        List<Integer> possibleAltAnswerCountsExceptCorrectOne = generatePossibleAltAnswerCountsExceptCorrectOne();

        TaskCondition taskCondition = new TaskCondition(n, m, scheduleType, schedule, possibleAltAnswerCountsExceptCorrectOne);
        return new Task(taskCondition);
    }

    private static List<Integer> generatePossibleAltAnswerCountsExceptCorrectOne() {
        Set<Integer> result = new HashSet<>();
        for (int i = 1; i < AMOUNT_OF_POSSIBLE_ALT_ANSWER_COUNTS; i++) {
            result.add(getRandomInt(ALT_ANSWER_COUNT_FROM_INCLUSIVE, ALT_ANSWER_COUNT_TO_EXCLUSIVE));
            if (result.size() != i) {
                i -= 1;
            }
        }
        return new ArrayList<>(result);
    }

    private static List<ScheduleElement> generateSchedule(int n) {
        List<ScheduleElement> result = new ArrayList<>();
        do {
            result.clear();
            for (int i = 1; i <= n; i++) {
                int t = getRandomInt(T_FROM_INCLUSIVE, T_TO_INCLUSIVE);
                if (getTimeValueDuplicateGroupsAmount(result, t) > EXPECTED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT) {
                    i -= 1;
                    continue;
                }
                int d = getRandomInt(D_FROM_INCLUSIVE, D_TO_EXCLUSIVE);
                int u = getRandomInt(U_FROM_INCLUSIVE, U_TO_INCLUSIVE);
                result.add(new ScheduleElement(i, t, d, u));
            }
        } while (getTimeValueDuplicateGroupsAmount(result) != EXPECTED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT);
        return result;
    }

    private static ScheduleType getRandomScheduleType() {
        ScheduleType[] scheduleTypes = ScheduleType.values();
        int randIndex = getRandomInt(0, scheduleTypes.length);
        return scheduleTypes[randIndex];
    }

    private static int getTimeValueDuplicateGroupsAmount(List<ScheduleElement> schedule) {
        List<Integer> timeValues = schedule
                .stream()
                .map(ScheduleElement::getT)
                .collect(toList());
        return (int) timeValues
                .stream()
                .distinct()
                .filter(t -> getElementEntriesCount(timeValues, t) > 1)
                .count();
    }

    private static int getTimeValueDuplicateGroupsAmount(List<ScheduleElement> schedule, int newT) {
        List<Integer> timeValues = schedule
                .stream()
                .map(ScheduleElement::getT)
                .collect(toList());
        timeValues.add(newT);
        return (int) timeValues
                .stream()
                .distinct()
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
