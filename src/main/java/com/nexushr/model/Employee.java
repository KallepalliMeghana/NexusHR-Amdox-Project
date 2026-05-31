package com.nexushr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @Email
    @Column(unique = true)
    private String email;

    private String department;
    private String designation;

    @Positive
    private double salary;

    private LocalDate joiningDate;
    private int performanceScore;
    private String attritionRisk;

    public Employee() {}

    public Employee(String fullName, String email, String department, String designation,
                    double salary, LocalDate joiningDate, int performanceScore) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.performanceScore = performanceScore;
        this.attritionRisk = "LOW";
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public int getPerformanceScore() { return performanceScore; }
    public String getAttritionRisk() { return attritionRisk; }

    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
    public void setPerformanceScore(int performanceScore) { this.performanceScore = performanceScore; }
    public void setAttritionRisk(String attritionRisk) { this.attritionRisk = attritionRisk; }
}
