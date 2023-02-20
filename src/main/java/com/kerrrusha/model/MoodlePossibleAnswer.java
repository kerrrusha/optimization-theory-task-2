package com.kerrrusha.model;

import lombok.Getter;

@Getter
public class MoodlePossibleAnswer {

    private static int objectsCreated = 0;

    private final int id;
    private final String answer;
    private final String possibleAnswerGroup;

    public MoodlePossibleAnswer(String answer, String possibleAnswerGroup) {
        this.id = ++objectsCreated;
        this.answer = answer;
        this.possibleAnswerGroup = possibleAnswerGroup;
    }

}
