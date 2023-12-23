package com.collaboration.collaboration.service;

import com.collaboration.collaboration.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.collaboration.collaboration.model.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final PairService pairService;
    private final String CSV_FILE_PATH = "D:/Sirma/collaboration/src/main/resources/file.csv";
    @Autowired
    public EmployeeServiceImp(PairService pairService) {
        this.pairService = pairService;
    }

    @Override
    public List<Employee> parseCsv(String CSV_FILE_PATH) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    int empId = Integer.parseInt(parts[0]);
                    int projectId = Integer.parseInt(parts[1]);

                    LocalDate dateFrom = parseDate(parts[2]);
                    LocalDate dateTo = parseDateWithNull(parts[3]);

                    Employee employee = new Employee(empId, projectId, dateFrom, dateTo);
                    employees.add(employee);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private LocalDate parseDate(String dateStr) throws ParseException {
        String[] dateFormats = {
                "dd-MM-yyyy",
                "yyyy-MM-dd",
                "MM-dd-yyyy"
        };

        for (String dateFormat : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {

            }
        }

        throw new ParseException("Unable to parse date: " + dateStr, 0);
    }

    private LocalDate parseDateWithNull(String dateStr) throws ParseException {
        if ("NULL".equals(dateStr)) {
            return LocalDate.now();
        } else {
            return parseDate(dateStr);
        }
    }

    @Override
    public List<Pair> findLongestWorkingPairs(List<Employee> employees) {
        return pairService.findLongestWorkingPairs(employees);
    }
}