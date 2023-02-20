package com.kerrrusha.model;

import com.kerrrusha.solving.TaskSolver;
import lombok.Getter;

@Getter
public class Task {

    private final TaskCondition taskCondition;
    private final TaskAnswer taskAnswer;

    public Task(TaskCondition taskCondition) {
        this.taskCondition = taskCondition;
        this.taskAnswer = new TaskSolver(this).solve();
        taskCondition.updatePossibleAltAnswerCounts(taskAnswer.altAnswersCount());
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskCondition=" + taskCondition +
                ", taskAnswer=" + taskAnswer +
                '}';
    }
}
