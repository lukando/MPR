package service;

import exception.InvalidDataException;
import model.Employee;
import model.ImportSummary;
import model.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportService {

    private final EmployeeService employeeService;

    public ImportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ImportSummary importFromCsv(String filePath) {
        int importedCount = 0;
        List<String> errors = new ArrayList<>();
        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Employee employee = parseLineToEmployee(line);

                    boolean added = employeeService.addEmployee(employee);

                    if (added) {
                        importedCount++;
                    } else {
                        throw new InvalidDataException("Email '" + employee.getEmail() + "' już istnieje w systemie.");
                    }

                } catch (InvalidDataException e) {
                    errors.add("Linia " + lineNumber + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            errors.add("Błąd krytyczny odczytu pliku: " + e.getMessage());
        }

        return new ImportSummary(importedCount, errors);
    }

    private Employee parseLineToEmployee(String line) throws InvalidDataException {
        String[] parts = line.split(",");

        if (parts.length != 6) {
            throw new InvalidDataException("Niepoprawna liczba kolumn (oczekiwano 6, jest " + parts.length + ").");
        }

        String firstName = parts[0].trim();
        String lastName = parts[1].trim();
        String email = parts[2].trim();
        String company = parts[3].trim();
        String positionStr = parts[4].trim();
        String salaryStr = parts[5].trim();

        Position position;
        try {
            position = Position.valueOf(positionStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Nieznane stanowisko: '" + positionStr + "'.");
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Niepoprawny format pensji: '" + salaryStr + "'.");
        }

        if (salary <= 0) {
            throw new InvalidDataException("Wynagrodzenie musi być dodatnie (jest: " + salary + ").");
        }

        return new Employee(firstName, lastName, email, company, position, salary);
    }
}