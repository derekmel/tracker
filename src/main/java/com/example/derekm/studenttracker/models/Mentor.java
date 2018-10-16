package com.example.derekm.studenttracker.models;

public class Mentor {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long courseId;

    public Mentor(long id, String name, String phone, String email, long courseId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", courseId'" + courseId + '\'' +
                '}';

    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getmentorname() {
        return name;
    }

    public void setmentorname(String name) {
        this.name = name;
    }

    public String getmentorphone() {
        return phone;
    }

    public void setmentorphone(String phone) {
        this.phone = phone;
    }

    public String getmentoremail() {
        return email;
    }

    public void setmentoremail(String email) {
        this.email = email;
    }

    public Long getcourseId() { return courseId; }

    public void setcourseId(Long courseId) { this.courseId = courseId; }
}
