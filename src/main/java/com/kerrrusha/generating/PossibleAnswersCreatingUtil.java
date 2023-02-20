package com.kerrrusha.generating;

import com.kerrrusha.model.MoodlePossibleAnswer;
import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.Task;

import java.util.ArrayList;
import java.util.List;

public class PossibleAnswersCreatingUtil {

    public static List<MoodlePossibleAnswer> create(Task task) {
        List<MoodlePossibleAnswer> possibleAnswerList = new ArrayList<>();

        possibleAnswerList.add(new MoodlePossibleAnswer("(", "1"));
        possibleAnswerList.add(new MoodlePossibleAnswer(")", "1"));

        List<ScheduleElement> scheduleElements = task.getTaskCondition().getInputSchedule();
        for (ScheduleElement scheduleElement : scheduleElements) {
            possibleAnswerList.add(new MoodlePossibleAnswer(scheduleElement.toShortString(), "1"));
        }

        List<Integer> possibleAltAnswerCounts = task.getTaskCondition().getPossibleAltAnswerCounts();
        for (Integer possibleAltAnswerCount : possibleAltAnswerCounts) {
            possibleAnswerList.add(new MoodlePossibleAnswer(possibleAltAnswerCount + "", "2"));
        }

        return possibleAnswerList;
    }

}
