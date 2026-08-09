package com.adityasingh.studentplacementportal.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "placement")
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placementId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "placement_date")
    private LocalDate placementDate;

    @Column(name = "status")
    private String status;


    // No-Argument Constructor
    public Placement() {

    }


    // Parameterized Constructor
    public Placement(Student student, Company company,
                     LocalDate placementDate, String status) {

        this.student = student;
        this.company = company;
        this.placementDate = placementDate;
        this.status = status;
    }


    // Getters and Setters

    public int getPlacementId() {
        return placementId;
    }

    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDate getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(LocalDate placementDate) {
        this.placementDate = placementDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}