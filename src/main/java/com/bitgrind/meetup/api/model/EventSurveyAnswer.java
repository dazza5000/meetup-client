package com.bitgrind.meetup.api.model;

public class EventSurveyAnswer {
    public long questionId;

    public String question;

    public String answer;

    public long updated;

    @Override
    public String toString() {
        return "EventSurveyAnswer{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", updated=" + updated +
                '}';
    }
}
