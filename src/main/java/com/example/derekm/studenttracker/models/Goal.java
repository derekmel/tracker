package com.example.derekm.studenttracker.models;

public class Goal {

    private long goalDateId;
    private String description;
    private String datetime;
    private long assessmentId;

    public Goal(long goalDateId, String description, String datetime, long assessmentId) {
        this.goalDateId = goalDateId;
        this.description = description;
        this.datetime = datetime;
        this.assessmentId = assessmentId;
    }

    public long getGoalDateId() {
        return goalDateId;
    }

    public void setGoalDateId(long goalDateId) {
        this.goalDateId = goalDateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(long assessmentId) {
        this.assessmentId = assessmentId;
    }
}
