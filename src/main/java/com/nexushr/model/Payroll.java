package com.nexushr.model;

import jakarta.persistence.*;

@Entity
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String month;
    private double basicSalary;
    private double allowance;
    private double deductions;
    private double netSalary;

    public Payroll() {}

    public Payroll(Long employeeId, String month, double basicSalary,
                   double allowance, double deductions, double netSalary) {
        this.employeeId = employeeId;
        this.month = month;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public String getMonth() { return month; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowance() { return allowance; }
    public double getDeductions() { return deductions; }
    public double getNetSalary() { return netSalary; }

    public void setId(Long id) { this.id = id; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setMonth(String month) { this.month = month; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setAllowance(double allowance) { this.allowance = allowance; }
    public void setDeductions(double deductions) { this.deductions = deductions; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
}
