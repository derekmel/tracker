package com.example.derekm.studenttracker.models;



public class Assessment {

    private long assessmentId;
    private String name;
    private String type;
    private String due;
    private long courseId;

    public Assessment(long assessmentId, String name, String type, String due, long courseId) {
        this.assessmentId = assessmentId;
        this.name = name;
        this.type = type;
        this.due = due;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "id=" + assessmentId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", due='" + due + '\'' +
                ", courseId=" + courseId +
                '}';
    }

    public long getId() {
        return assessmentId;
    }

    public void setiId(long id) {
        this.assessmentId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
