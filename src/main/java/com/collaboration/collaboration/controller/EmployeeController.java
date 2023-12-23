package com.collaboration.collaboration.controller;

import com.collaboration.collaboration.model.Employee;
import com.collaboration.collaboration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/view")
    public String viewCsvData(Model model) {
        List<Employee> employees = employeeService.parseCsv("D:/Sirma/collaboration/src/main/resources/file.csv");
        model.addAttribute("employees", employees);
        return "view";
    }
}