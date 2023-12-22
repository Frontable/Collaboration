package com.collaboration.collaboration.service;

import com.collaboration.collaboration.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class PairService {
    public List<String> findWorkingPairs(List<Employee> employees) {
        List<String> workingPairs = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            Employee emp1 = employees.get(i);

            for (int j = i + 1; j < employees.size(); j++) {
                Employee emp2 = employees.get(j);

                // Check if employees have overlapping periods on the same project
                if (emp1.getProjectId() == emp2.getProjectId() &&
                        overlap(emp1.getDateFrom(), emp1.getDateTo(), emp2.getDateFrom(), emp2.getDateTo())) {
                    // Format the working pair as a string and add it to the list
                    String workingPair = "Working Pair: " + emp1.getEmpId() + " and " + emp2.getEmpId() +
                            " on Project " + emp1.getProjectId() +
                            " from " + emp1.getDateFrom() + " to " + emp1.getDateTo();
                    workingPairs.add(workingPair);
                }
            }
        }

        return workingPairs;
    }

    // Helper method to check if two date ranges overlap
    private boolean overlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }
}
