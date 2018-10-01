package com.example.derekm.studenttracker.models;

public class Goal {

    private int goalDateId;
    private String description;
    private String datetime;
    private int assessmentId;

    public Goal(int goalDateId, String description, String datetime, int assessmentId) {
        this.goalDateId = goalDateId;
        this.description = description;
        this.datetime = datetime;
        this.assessmentId = assessmentId;
    }

    public int getGoalDateId() {
        return goalDateId;
    }

    public void setGoalDateId(int goalDateId) {
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

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }
}
