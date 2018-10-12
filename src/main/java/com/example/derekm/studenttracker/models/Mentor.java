package com.example.derekm.studenttracker.models;

public class Mentor {
    private long id;
    private String name;
    private String phone;
    private String email;

    public Mentor(String name, String phone, String email) {
        //this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
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


}
