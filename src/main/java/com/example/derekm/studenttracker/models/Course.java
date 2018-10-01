package com.example.derekm.studenttracker.models;

public class Course {
    private long courseId;
    private String name;
    private String start;
    private String end;
    private String status;
    private long termId;


    public Course(long courseId, String name, String start, String end, String status, long termId) {
        this.courseId = courseId;
        this.name = name;
        this.start = start;
        this.end = end;
        this.status = status;
        this.termId = termId;
    }

    public String toString() {
        return "Course{" +
                "id=" + courseId +
                ", name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", status'" + status + '\'' +
                ", termId'" + termId + '\'' +
                '}';
    }

    public long getId() { return this.courseId; }

    public void setId(long id) {
        this.courseId = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }



}
