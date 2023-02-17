package com.kerrrusha.solving;

import com.kerrrusha.model.ScheduleElement;
import com.kerrrusha.model.Task;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static com.kerrrusha.Util.factorial;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class TaskSolver {

    private final Task task;

    @Getter
    private List<ScheduleElement> solved;

    public String solve() {
        return getSolvedSchedule() + System.lineSeparator() +
                "Alt answers count: " + getAltAnswersCount();
    }

    public List<ScheduleElement> getSolvedSchedule() {
        if (nonNull(solved)) {
            return solved;
        }
        solved = task.getSchedule().stream()
                .sorted(Comparator.comparing(ScheduleElement::getI))
                .sorted(Comparator.comparing(ScheduleElement::getT).reversed())
                .collect(toList());
        return solved;
    }

    public int getAltAnswersCount() {
        solveIfNotSolved();
        Map<Integer, Integer> timeValueEntriesToCountMap = getUniqueTimeValues()
                .stream()
                .collect(toMap(t -> t, this::getEntriesCountForTime));
        int elementsThatCauseAltAnswersCount = timeValueEntriesToCountMap
                .keySet().stream()
                .filter(t -> timeValueEntriesToCountMap.get(t) > 1)
                .map(timeValueEntriesToCountMap::get)
                .mapToInt(Integer::valueOf)
                .sum();
        return factorial(elementsThatCauseAltAnswersCount);
    }

    private void solveIfNotSolved() {
        if (isNull(solved)) {
            solved = getSolvedSchedule();
        }
    }

    private Integer getEntriesCountForTime(Integer t) {
        return Math.toIntExact(solved.stream()
                .filter(e -> e.getT() == t)
                .count());
    }

    private Set<Integer> getUniqueTimeValues() {
        return solved.stream()
                .map(ScheduleElement::getT)
                .collect(toSet());
    }

}
