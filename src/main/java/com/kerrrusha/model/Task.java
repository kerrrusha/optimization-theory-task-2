package com.kerrrusha.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Task {

    private final int n;
    private final int m;
    private final List<ScheduleElement> schedule;

    @Override
    public String toString() {
        return "Task{" +
                "n=" + n +
                ", m=" + m +
                ", schedule=" + schedule +
                '}';
    }
}
