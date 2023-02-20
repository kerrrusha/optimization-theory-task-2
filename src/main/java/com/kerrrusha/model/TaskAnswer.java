package com.kerrrusha.model;

import java.util.List;

public record TaskAnswer(
        List<ScheduleElement> schedule,
        int altAnswersCount
) {

    @Override
    public String toString() {
        return "TaskAnswer{" +
                "schedule=" + schedule +
                ", altAnswersCount=" + altAnswersCount +
                '}';
    }

}
