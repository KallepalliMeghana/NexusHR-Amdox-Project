package com.nexushr.controller;

import com.nexushr.model.Attendance;
import com.nexushr.model.Employee;
import com.nexushr.model.LeaveRequest;
import com.nexushr.repository.AttendanceRepository;
import com.nexushr.repository.LeaveRequestRepository;
import com.nexushr.service.EmployeeService;
import com.nexushr.service.PayrollService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class WebController {
    private final EmployeeService employeeService;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final PayrollService payrollService;

    public WebController(EmployeeService employeeService,
                         AttendanceRepository attendanceRepository,
                         LeaveRequestRepository leaveRequestRepository,
                         PayrollService payrollService) {
        this.employeeService = employeeService;
        this.attendanceRepository = attendanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.payrollService = payrollService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("employeeCount", employeeService.findAll().size());
        model.addAttribute("attendanceCount", attendanceRepository.findAll().size());
        model.addAttribute("leaveCount", leaveRequestRepository.findAll().size());
        model.addAttribute("payrollCount", payrollService.findAll().size());
        model.addAttribute("employees", employeeService.findAll());
        return "dashboard";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("employee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee) {
        if (employee.getJoiningDate() == null) {
            employee.setJoiningDate(LocalDate.now());
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    @GetMapping("/attendance")
    public String attendance(Model model) {
        model.addAttribute("attendanceList", attendanceRepository.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return "attendance";
    }

    @PostMapping("/attendance")
    public String markAttendance(@RequestParam Long employeeId, @RequestParam String status) {
        Attendance attendance = new Attendance(employeeId, LocalDate.now(), status);
        attendanceRepository.save(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/leaves")
    public String leaves(Model model) {
        model.addAttribute("leaves", leaveRequestRepository.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return "leaves";
    }

    @PostMapping("/leaves")
    public String applyLeave(@RequestParam Long employeeId,
                             @RequestParam String leaveType,
                             @RequestParam String startDate,
                             @RequestParam String endDate,
                             @RequestParam String reason) {
        LeaveRequest leave = new LeaveRequest(
                employeeId,
                leaveType,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                reason,
                "PENDING"
        );
        leaveRequestRepository.save(leave);
        return "redirect:/leaves";
    }

    @GetMapping("/leaves/status/{id}/{status}")
    public String updateLeaveStatus(@PathVariable Long id, @PathVariable String status) {
        LeaveRequest leave = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(status);
        leaveRequestRepository.save(leave);
        return "redirect:/leaves";
    }

    @GetMapping("/payroll")
    public String payroll(Model model) {
        model.addAttribute("payrolls", payrollService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return "payroll";
    }

    @PostMapping("/payroll")
    public String generatePayroll(@RequestParam Long employeeId, @RequestParam String month) {
        payrollService.generatePayroll(employeeId, month);
        return "redirect:/payroll";
    }
}
