package com.collaboration.collaboration.controller;

import com.collaboration.collaboration.model.Employee;
import com.collaboration.collaboration.model.Pair;
import com.collaboration.collaboration.service.EmployeeService;
import com.collaboration.collaboration.service.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/pairs")
public class PairController {
    private final EmployeeService employeeService;
    private final PairService pairService;

    @Autowired
    public PairController(EmployeeService employeeService, PairService pairService) {
        this.employeeService = employeeService;
        this.pairService = pairService;
    }

    @GetMapping("/longest")
    public List<Pair> getLongestWorkingPairs() {
        List<Employee> employees = employeeService.parseCsv("C:/Users/Simeon/Downloads/demo/src/main/resources/file.csv");
        return pairService.findLongestWorkingPairs(employees);
    }
}

