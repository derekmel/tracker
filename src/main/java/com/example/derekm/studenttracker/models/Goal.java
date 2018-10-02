package com.example.derekm.studenttracker.models;

public class Goal {

    private long goalDateId;
    public String description;
    public String date;
    private long assessmentId;

    public Goal(long goalDateId, String description, String date, long assessmentId) {
        this.goalDateId = goalDateId;
        this.description = description;
        this.date = date;
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
        return date;
    }

    public void setDatetime(String datetime) {
        this.date = date;
    }

    public long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(long assessmentId) {
        this.assessmentId = assessmentId;
    }
}
