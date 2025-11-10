import exception.ApiException;
import model.CompanyStatistics;
import model.Employee;
import model.ImportSummary;
import service.ApiService;
import service.EmployeeService;
import service.ImportService;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        ImportService importService = new ImportService(employeeService);
        ApiService apiService = new ApiService();

        System.out.println("=== START SYSTEMU ZARZĄDZANIA PRACOWNIKAMI ===");

        System.out.println("\n--- 1. Importowanie danych z employees.csv ---");
        ImportSummary summary = importService.importFromCsv("employees.csv");

        System.out.println("Pomyślnie zaimportowano: " + summary.getImportedCount() + " pracowników.");
        if (!summary.getErrors().isEmpty()) {
            System.out.println("Wystąpiły błędy podczas importu:");
            for (String error : summary.getErrors()) {
                System.err.println("  -> " + error);
            }
        }

        System.out.println("\n--- 2. Pobieranie danych z REST API ---");
        try {
            List<Employee> apiEmployees = apiService.fetchEmployeesFromApi();
            System.out.println("Pobrano " + apiEmployees.size() + " użytkowników z API.");

            int addedFromApi = 0;
            for (Employee emp : apiEmployees) {
                if (employeeService.addEmployee(emp)) {
                    addedFromApi++;
                }
            }
            System.out.println("Dodano " + addedFromApi + " nowych pracowników do serwisu (pomięto duplikaty mailowe).");

        } catch (ApiException e) {
            System.err.println("BŁĄD KRYTYCZNY API: Nie udało się pobrać danych z API.");
            e.printStackTrace();
        }

        System.out.println("\n--- 3. Lista wszystkich pracowników w systemie ---");
        List<Employee> allEmployees = employeeService.getAllEmployees();
        System.out.println("Łączna liczba pracowników: " + allEmployees.size());
        for (Employee emp : allEmployees) {
            System.out.println("  -> " + emp);
        }

        System.out.println("\n--- 4. Analiza: Pracownicy z pensją poniżej bazowej ---");
        List<Employee> inconsistent = employeeService.validateSalaryConsistency();
        if (inconsistent.isEmpty()) {
            System.out.println("Brak niespójności płacowych.");
        } else {
            for (Employee emp : inconsistent) {
                System.out.println("  -> " + emp.getFirstName() + " " + emp.getLastName() +
                        " (Pensja: " + emp.getSalary() +
                        ", Baza: " + emp.getPosition().getBazowaPensja() + ")");
            }
        }

        System.out.println("\n--- 5. Analiza: Statystyki firm ---");
        Map<String, CompanyStatistics> stats = employeeService.getCompanyStatistics();

        for (Map.Entry<String, CompanyStatistics> entry : stats.entrySet()) {
            System.out.println("Firma: " + entry.getKey());
            System.out.println("    " + entry.getValue());
        }

        System.out.println("\n=== ZAKOŃCZONO DZIAŁANIE ===");
    }
}