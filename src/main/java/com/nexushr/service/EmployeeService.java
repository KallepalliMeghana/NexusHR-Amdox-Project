package com.nexushr.service;

import com.nexushr.model.Employee;
import com.nexushr.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        employee.setAttritionRisk(calculateAttritionRisk(employee));
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public String calculateAttritionRisk(Employee employee) {
        long months = employee.getJoiningDate() == null ? 0 :
                ChronoUnit.MONTHS.between(employee.getJoiningDate(), LocalDate.now());

        int score = employee.getPerformanceScore();

        if (score < 45 && months < 12) {
            return "HIGH";
        } else if (score < 65) {
            return "MEDIUM";
        }
        return "LOW";
    }
}
