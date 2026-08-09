package com.adityasingh.studentplacementportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "package_lpa")
    private double packageLpa;

    @Column(name = "minimum_cgpa")
    private double minimumCgpa;

    // No-Argument Constructor
    public Company() {

    }

    // Parameterized Constructor
    public Company(String companyName, String location,
                   double packageLpa, double minimumCgpa) {

        this.companyName = companyName;
        this.location = location;
        this.packageLpa = packageLpa;
        this.minimumCgpa = minimumCgpa;
    }

    // Getters and Setters

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPackageLpa() {
        return packageLpa;
    }

    public void setPackageLpa(double packageLpa) {
        this.packageLpa = packageLpa;
    }

    public double getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(double minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }
}