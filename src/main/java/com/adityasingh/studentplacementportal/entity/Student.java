package com.adityasingh.studentplacementportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    // No-Argument Constructor
public Student() {

}

public Student(String name, String email, String phone,
               String course, String branch,
               double cgpa, int passingYear) {

    this.name = name;
    this.email = email;
    this.phone = phone;
    this.course = course;
    this.branch = branch;
    this.cgpa = cgpa;
    this.passingYear = passingYear;
}

public int getStudentId() {
    return studentId;
}

public void setStudentId(int studentId) {
    this.studentId = studentId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getCourse() {
    return course;
}

public void setCourse(String course) {
    this.course = course;
}

public String getBranch() {
    return branch;
}

public void setBranch(String branch) {
    this.branch = branch;
}

public double getCgpa() {
    return cgpa;
}

public void setCgpa(double cgpa) {
    this.cgpa = cgpa;
}

public int getPassingYear() {
    return passingYear;
}

public void setPassingYear(int passingYear) {
    this.passingYear = passingYear;
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "course")
    private String course;

    @Column(name = "branch")
    private String branch;

    @Column(name = "cgpa")
    private double cgpa;

    @Column(name = "passing_year")
    private int passingYear;

}