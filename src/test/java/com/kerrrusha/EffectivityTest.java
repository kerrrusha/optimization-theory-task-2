package com.kerrrusha;

import com.kerrrusha.config.ConfigReader;
import com.kerrrusha.generating.TaskGenerator;
import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kerrrusha.generating.TaskGenerator.getTimeValueDuplicateGroupsAmount;
import static com.kerrrusha.util.ConverterUtil.toInt;
import static com.kerrrusha.util.TaskUtil.getRandomInt;

public class EffectivityTest {

    private final TaskGenerator taskGenerator = new TaskGenerator();
    private final ConfigReader configReader = new ConfigReader();

    @Test
    public void executionTimeTest() {
        TaskGenerator taskGenerator = new TaskGenerator();
        Task temp = taskGenerator.generateSingleTask(); //in purpose of JVM load this class before time tests
        temp.getId(); //in purpose of JVM not to skip the line with the task generating

        List<Integer> nValues = Arrays.asList(10, 55, 100);
        int m = 10;

        for (int n : nValues) {
            for (int i = 0; i < m; i++) {
                long startTime = System.nanoTime();

                Task task = taskGenerator.generateSingleTask(n);

                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                double milliseconds = executionTime / 1000000.0;

                task.getId(); //in purpose of JVM not to skip the line with the task generating
                System.out.print(milliseconds);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void accuracyTest() {
        int EXPECTED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT = toInt(configReader.getProperty("EXPECTED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT"));
        List<Integer> nValues = Arrays.asList(10, 55, 100);
        int m = 10;

        for (int n : nValues) {
            for (int i = 0; i < m; i++) {
                Task task = taskGenerator.generateSingleTask(n);
                int timeValueDuplicateGroupsAmount = getTimeValueDuplicateGroupsAmount(task.getTaskCondition().getInputSchedule());

                System.out.print(EXPECTED_TIME_VALUE_DUPLICATE_GROUPS_AMOUNT == timeValueDuplicateGroupsAmount);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void iterationsTest() {
        class CustomIterationsTaskGenerator extends TaskGenerator {

            public List<ScheduleElement> generateSchedule(int n, int N) {
                List<ScheduleElement> result = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    result.clear();
                    for (int i = 1; i <= n; i++) {
                        int t = getRandomInt(T_FROM_INCLUSIVE, T_TO_INCLUSIVE);
                        int d = getRandomInt(D_FROM_INCLUSIVE, D_TO_EXCLUSIVE);
                        int u = getRandomInt(U_FROM_INCLUSIVE, U_TO_INCLUSIVE);
                        result.add(new ScheduleElement(i, t, d, u));
                    }
                }
                return result;
            }

        }

        CustomIterationsTaskGenerator generator = new CustomIterationsTaskGenerator();
        List<ScheduleElement> temp = generator.generateSchedule(1, 1); //in purpose of JVM load this class before time tests
        temp.size(); //in purpose of JVM not to skip the line with the task generating

        List<Integer> NValues = Arrays.asList(1, 3, 5);
        int m = 10;
        int nDefault = 10;

        for (int N : NValues) {
            for (int i = 0; i < m; i++) {
                long startTime = System.nanoTime();

                List<ScheduleElement> schedule = generator.generateSchedule(nDefault, N);

                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                double milliseconds = executionTime / 1000000.0;

                schedule.size(); //in purpose of JVM not to skip the line with the task generating
                System.out.print(milliseconds);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
