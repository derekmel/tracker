package com.example.derekm.studenttracker.models;

public class Note {

    private long id;
    private String note;
    private long courseId;

    public Note(long id, String note, long courseId) {
        this.id = id;
        this.note = note;
        this.courseId = courseId;
    }

    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", courseId='" + courseId + '\'' +  // what does this \ do?
                '}';
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public long getCourseId() { return this.courseId; }

    public void setCourseId(long courseId) { this.courseId = courseId; }
}
