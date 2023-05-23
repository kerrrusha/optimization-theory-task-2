package com.kerrrusha;

import com.kerrrusha.generating.TaskGenerator;
import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.ScheduleType;
import com.kerrrusha.model.Task;
import com.kerrrusha.model.TaskCondition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolvingTest {

    private final TaskGenerator taskGenerator = new TaskGenerator();

    @Test
    public void solveSmallAmount() {
        List<ScheduleElement> inputSchedule = Arrays.asList(
                new ScheduleElement(1, 8, 0, 0),
                new ScheduleElement(2, 14, 0, 0),
                new ScheduleElement(3, 6, 0, 0),
                new ScheduleElement(4, 6, 0, 0),
                new ScheduleElement(5, 9, 0, 0)
        );
        TaskCondition condition = new TaskCondition(5, 1, ScheduleType.MAX_T, inputSchedule, new ArrayList<>());

        long startTime = System.nanoTime();
        Task task = new Task(condition);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double milliseconds = executionTime / 1000000.0;

        System.out.println("Час роботи (мс): " + milliseconds);

        System.out.println();
        System.out.println("Розв'язаний розклад:");
        System.out.println(task.getTaskAnswer().getSchedule());

        System.out.println();
        System.out.println("Кількість альтернативних розв'язків: " + task.getTaskAnswer().getAltAnswersCount());
    }

    @Test
    public void solveMediumAmount() {
        final int n = 100;

        ScheduleType scheduleType = taskGenerator.getRandomScheduleType();
        List<ScheduleElement> schedule = taskGenerator.generateSchedule(n);
        List<String> possibleAltAnswerCountsExceptCorrectOne = taskGenerator.generatePossibleAltAnswerCountsExceptCorrectOne();

        TaskCondition taskCondition = new TaskCondition(n, 1, scheduleType, schedule, possibleAltAnswerCountsExceptCorrectOne);

        long startTime = System.nanoTime();
        Task task = new Task(taskCondition);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double milliseconds = executionTime / 1000000.0;

        System.out.println("Час роботи (мс): " + milliseconds);

        System.out.println();
        System.out.println("Розв'язаний розклад:");
        prettyListPrint(task.getTaskAnswer().getSchedule());

        System.out.println();
        System.out.println("Кількість альтернативних розв'язків: " + task.getTaskAnswer().getAltAnswersCount());
    }

    @Test
    public void solveLargeAmount() {
        final int n = 1000;

        ScheduleType scheduleType = taskGenerator.getRandomScheduleType();
        List<ScheduleElement> schedule = taskGenerator.generateSchedule(n);
        List<String> possibleAltAnswerCountsExceptCorrectOne = taskGenerator.generatePossibleAltAnswerCountsExceptCorrectOne();

        TaskCondition taskCondition = new TaskCondition(n, 1, scheduleType, schedule, possibleAltAnswerCountsExceptCorrectOne);

        long startTime = System.nanoTime();
        Task task = new Task(taskCondition);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double milliseconds = executionTime / 1000000.0;

        System.out.println("Час роботи (мс): " + milliseconds);

        System.out.println();
        System.out.println("Розв'язаний розклад:");
        prettyListPrint(task.getTaskAnswer().getSchedule());

        System.out.println();
        System.out.println("Кількість альтернативних розв'язків: " + task.getTaskAnswer().getAltAnswersCount());
    }

    private void prettyListPrint(List<ScheduleElement> schedule) {
        for (int i = 0; i < schedule.size(); i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.print(schedule.get(i));
            System.out.print(", ");
        }
    }

}
