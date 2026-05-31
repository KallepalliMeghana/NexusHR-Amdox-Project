package com.nexushr.service;

import com.nexushr.model.Employee;
import com.nexushr.model.Payroll;
import com.nexushr.repository.PayrollRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeService employeeService;

    public PayrollService(PayrollRepository payrollRepository, EmployeeService employeeService) {
        this.payrollRepository = payrollRepository;
        this.employeeService = employeeService;
    }

    public Payroll generatePayroll(Long employeeId, String month) {
        Employee employee = employeeService.findById(employeeId);
        double basic = employee.getSalary();
        double allowance = basic * 0.20;
        double deductions = basic * 0.10;
        double net = basic + allowance - deductions;

        Payroll payroll = new Payroll(employeeId, month, basic, allowance, deductions, net);
        return payrollRepository.save(payroll);
    }

    public List<Payroll> findAll() {
        return payrollRepository.findAll();
    }
}
