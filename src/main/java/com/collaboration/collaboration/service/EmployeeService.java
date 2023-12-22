package com.collaboration.collaboration.service;

import com.collaboration.collaboration.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> parseCsv(String csvFilePath);
    List<String> findLongestWorkingPairs(List<Employee> employees);
}
