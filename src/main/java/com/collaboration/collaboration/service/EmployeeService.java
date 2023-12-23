package com.collaboration.collaboration.service;

import com.collaboration.collaboration.model.Employee;
import com.collaboration.collaboration.model.Pair;

import java.util.List;

public interface EmployeeService {
    List<Employee> parseCsv(String csvFilePath);
    List<Pair> findLongestWorkingPairs(List<Employee> employees);
}
