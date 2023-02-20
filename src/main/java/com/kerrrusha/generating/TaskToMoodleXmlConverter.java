package com.kerrrusha.generating;

import com.kerrrusha.model.MoodlePossibleAnswer;
import com.kerrrusha.model.Task;
import com.kerrrusha.util.FileReaderUtil;
import com.kerrrusha.util.FileWriterUtil;

import java.util.List;

public class TaskToMoodleXmlConverter {

    private static final String TASK_NAME_TAG = "{TASK_NAME}";
    private static final String N_TAG = "{N}";
    private static final String M_TAG = "{M}";
    private static final String SCHEDULE_TYPE_TAG = "{SCHEDULE_TYPE}";
    private static final String CORRECT_TASK_SCHEDULE_SCHEMA_TAG = "{CORRECT_TASK_SCHEDULE_SCHEMA}";
    private static final String CORRECT_ALT_ANSWERS_COUNT_ID_TAG = "{CORRECT_ALT_ANSWERS_COUNT_ID}";
    private static final String DRAGBOXES_TAG = "{DRAGBOXES}";

    private final Task task;
    private final List<MoodlePossibleAnswer> possibleAnswerList;

    private final String taskName;
    private final String resultFilename;
    private final String moodleXmlTemplate;

    private String resultMoodleXml;

    public TaskToMoodleXmlConverter(Task task, List<MoodlePossibleAnswer> possibleAnswerList) {
        this.task = task;
        this.possibleAnswerList = possibleAnswerList;

        taskName = TaskNameGeneratorUtil.generateTaskName();
        resultFilename = MoodleXmlFilenameGeneratorUtil.generateFilename();
        moodleXmlTemplate = FileReaderUtil.read("moodle-template.xml");
    }

    public void createMoodleXmlFile() {
        if (resultMoodleXml != null) {
            writeResultToFile();
        }

        resultMoodleXml = moodleXmlTemplate;
        setTaskName();
        setN();
        setM();

        //writeResultToFile();
    }

    private void setTaskName() {
        resultMoodleXml = resultMoodleXml.replace(TASK_NAME_TAG, taskName);
    }

    private void setN() {
        resultMoodleXml = resultMoodleXml.replace(M_TAG, task.getTaskCondition().getM() + "");
    }

    private void setM() {
        resultMoodleXml = resultMoodleXml.replace(N_TAG, task.getTaskCondition().getN() + "");
    }

    private void writeResultToFile() {
        FileWriterUtil.write(resultFilename, resultMoodleXml);
    }

}
