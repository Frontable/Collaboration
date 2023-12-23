package com.collaboration.collaboration.service;

import com.collaboration.collaboration.model.Employee;
import com.collaboration.collaboration.model.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PairService {

    public List<Pair> findLongestWorkingPairs(List<Employee> employees) {
        Map<Integer, Pair> longestPairsByProject = new HashMap<>();

        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                Employee emp1 = employees.get(i);
                Employee emp2 = employees.get(j);

                if (emp1.getProjectId() == emp2.getProjectId()) {
                    LocalDate startDate = (emp1.getDateFrom().isAfter(emp2.getDateFrom())) ? emp1.getDateFrom() : emp2.getDateFrom();
                    LocalDate endDate = (emp1.getDateTo().isBefore(emp2.getDateTo())) ? emp1.getDateTo() : emp2.getDateTo();
                    long daysWorked = ChronoUnit.DAYS.between(startDate, endDate) + 1;

                    if (daysWorked > 0) {
                        Pair currentPair = new Pair(emp1.getEmpId(), emp2.getEmpId(),
                                emp1.getProjectId(), daysWorked);
                        longestPairsByProject.merge(emp1.getProjectId(), currentPair, (existingPair, newPair) ->
                                existingPair.getDaysWorked() > newPair.getDaysWorked() ? existingPair : newPair);
                    }
                }
            }
        }

        return new ArrayList<>(longestPairsByProject.values());
    }
}
