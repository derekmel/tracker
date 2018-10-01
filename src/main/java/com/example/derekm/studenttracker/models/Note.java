package com.example.derekm.studenttracker.models;

public class Note {

    private long noteId;
    private String note;
    private long courseId;

    public Note(long noteId, String note, long courseId) {
        this.noteId = noteId;
        this.note = note;
        this.courseId = courseId;
    }

    public String toString() {
        return "Note{" +
                "noteid=" + noteId +
                ", note='" + note + '\'' +
                ", courseId='" + courseId + '\'' +  // what does this \ do?
                '}';
    }

    public long getNoteId() { return noteId; }

    public void setNoteId(long noteId) { this.noteId = noteId; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public long getCourseId() { return this.courseId; }

    public void setCourseId(long courseId) { this.courseId = courseId; }
}
