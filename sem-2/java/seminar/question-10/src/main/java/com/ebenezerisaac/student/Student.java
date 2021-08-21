package com.ebenezerisaac.student;

public class Student {
    private String name;
    private String birthday;
    private String email;
    private String course;
    private int semester;


    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public void setName(String newFirstName) {
        this.name = newFirstName;
    }

    public void setBirthday(String newSurname) {
        this.birthday = newSurname;
    }

    public void setEmail(String newCourse) {
        this.email = newCourse;
    }

    public void setCourse(String newCourse) {
        this.course = newCourse;
    }

    public void setSemester(int newSemester) {
        this.semester = newSemester;
    }
}