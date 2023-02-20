package com.kerrrusha;

import com.kerrrusha.generating.PossibleAnswersCreator;
import com.kerrrusha.generating.TaskToMoodleXmlConverter;
import com.kerrrusha.model.MoodlePossibleAnswer;
import com.kerrrusha.model.Task;

import java.util.List;

import static com.kerrrusha.generating.TaskGeneratorUtil.generateAmountOfTasks;

public class Main {

    public static void main(String[] args) {
        List<Task> tasks = generateAmountOfTasks(100);

        for (Task task : tasks) {
            List<MoodlePossibleAnswer> possibleAnswerList = new PossibleAnswersCreator(task).create();
            new TaskToMoodleXmlConverter(task, possibleAnswerList).createMoodleXmlFile();
        }
    }

}
