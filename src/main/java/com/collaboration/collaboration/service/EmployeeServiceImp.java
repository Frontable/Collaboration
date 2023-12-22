package com.collaboration.collaboration.service;

import com.collaboration.collaboration.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImp implements EmployeeService {
    private final PairService pairService;
    @Autowired
    public EmployeeServiceImp(PairService pairService) {
        this.pairService = pairService;
    }
    @Override
    public List<Employee> parseCsv(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int empId = Integer.parseInt(parts[0].trim());
                    int projectId = Integer.parseInt(parts[1].trim());
                    LocalDate dateFrom = LocalDate.parse(parts[2].trim(), DateTimeFormatter.ISO_DATE);
                    LocalDate dateTo = parts[3].trim().equalsIgnoreCase("NULL") ? LocalDate.now() : LocalDate.parse(parts[3].trim(), DateTimeFormatter.ISO_DATE);

                    Employee employee = new Employee(empId, projectId, dateFrom, dateTo);
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Log the parsed data
        employees.forEach(System.out::println);

        return employees;
    }


    private LocalDate parseDate(String dateString) {
        try {
            if ("NULL".equalsIgnoreCase(dateString)) {
                return LocalDate.now();
            } else {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(dateString).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            }
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public List<String> findLongestWorkingPairs(List<Employee> employees) {
        // Delegate the responsibility to PairService
        return pairService.findWorkingPairs(employees);
    }
}
