package com.kerrrusha.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleType {

    MAX_SUM_ENDING_TIME("максимальний сумарний час закінчення"),
    MIN_SUM_ENDING_TIME("мінімальний сумарний час закінчення");

    private final String printableName;

    @Override
    public String toString() {
        return "ScheduleType{" +
                "printableName='" + printableName + '\'' +
                '}';
    }
}
