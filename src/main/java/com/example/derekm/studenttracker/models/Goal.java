package com.example.derekm.studenttracker.models;

public class Goal {

    public String description;
    public String date;

    public Goal(String description, String date) {
        this.description = description;
        this.date = date;

    }

    @Override
    public String toString() {
        return "Goal{" +
                "description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

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

}
