package com.example.derekm.studenttracker.models;

public class Note {

    private int noteId;
    private String note;
    private int courseId;

    public Note(int noteId, String note, int courseId) {
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

    public int getNoteId() { return noteId; }

    public void setNoteId(int noteId) { this.noteId = noteId; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public int getCourseId() { return this.courseId; }

    public void setCourseId(int courseId) { this.courseId = courseId; }
}
