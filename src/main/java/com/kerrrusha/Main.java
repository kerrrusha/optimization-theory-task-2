package com.kerrrusha;

import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.ScheduleType;
import com.kerrrusha.model.Task;
import com.kerrrusha.solving.TaskSolver;

import java.util.List;

import static com.kerrrusha.generating.TaskGeneratorUtil.generateAmountOfTasks;

public class Main {

    public static void main(String[] args) {
        solveExample();
        System.out.println("####################");
        generateAndSolveAmount(10);
    }

    private static void generateAndSolveAmount(int amount) {
        List<Task> tasks = generateAmountOfTasks(amount);
        for (Task task : tasks) {
            showSolvingProcess(task);
        }
    }

    private static void solveExample() {
        Task example = new Task(5,
                1,
                ScheduleType.MAX_SUM_ENDING_TIME,
                List.of(
                new ScheduleElement(4, 9, 26, 1),
                new ScheduleElement(2, 14, 34, 2),
                new ScheduleElement(3, 10, 33, 3),
                new ScheduleElement(1, 14, 34, 1),
                new ScheduleElement(5, 7, 44, 2)
        ));

        showSolvingProcess(example);
    }

    private static void showSolvingProcess(Task task) {
        System.out.println(task);

        TaskSolver taskSolver = new TaskSolver(task);
        System.out.println("Solved schedule:");
        System.out.println(taskSolver.solve());

        System.out.println();
    }

}
