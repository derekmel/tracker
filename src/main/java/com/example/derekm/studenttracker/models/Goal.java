package com.example.derekm.studenttracker.models;

public class Goal {
    public long id;
    public String description;
    public String date;
    public long assessmentId;

    public Goal(long id, String description, String date, long assessmentId) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.assessmentId = assessmentId;

    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                "description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", assessmentId'" + assessmentId + '\'' +
                '}';
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAssessmentId() { return assessmentId; }

    public void setAssessmentId(long assessmentId) { this.assessmentId = assessmentId; }
}
