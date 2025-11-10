package service;

import model.CompanyStatistics;
import model.Employee;

import java.util.*;

public class EmployeeService {

    private final List<Employee> pracownicy = new ArrayList<>();

    public boolean addEmployee(Employee employee) {
        for (Employee istniejacy : pracownicy) {
            if (istniejacy.getEmail().equals(employee.getEmail())) {
                return false;
            }
        }
        pracownicy.add(employee);
        return true;
    }


    public List<Employee> getAllEmployees() {

        return new ArrayList<>(pracownicy);
    }


    public List<Employee> validateSalaryConsistency() {
        List<Employee> niespojniPracownicy = new ArrayList<>();

        for (Employee emp : pracownicy) {
            double pensjaBazowa = emp.getPosition().getBazowaPensja();
            if (emp.getSalary() < pensjaBazowa) {
                niespojniPracownicy.add(emp);
            }
        }
        return niespojniPracownicy;
    }


    public Map<String, CompanyStatistics> getCompanyStatistics() {

        Map<String, List<Employee>> pracownicyPoFirmie = new HashMap<>();

        for (Employee emp : pracownicy) {
            String nazwaFirmy = emp.getCompanyName();

            List<Employee> listaFirmy = pracownicyPoFirmie.get(nazwaFirmy);

            if (listaFirmy == null) {
                listaFirmy = new ArrayList<>();
                pracownicyPoFirmie.put(nazwaFirmy, listaFirmy);
            }

            listaFirmy.add(emp);
        }

        Map<String, CompanyStatistics> statystykiMap = new HashMap<>();

        for (Map.Entry<String, List<Employee>> entry : pracownicyPoFirmie.entrySet()) {
            String nazwaFirmy = entry.getKey();
            List<Employee> pracownicyFirmy = entry.getValue();

            int liczbaPracownikow = pracownicyFirmy.size();
            double sumaPensji = 0;
            double najwyzszaPensja = -1.0;
            String najlepiejZarabiajacy = "Brak";

            for (Employee empFirmy : pracownicyFirmy) {
                sumaPensji += empFirmy.getSalary();

                if (empFirmy.getSalary() > najwyzszaPensja) {
                    najwyzszaPensja = empFirmy.getSalary();
                    najlepiejZarabiajacy = empFirmy.getFirstName() + " " + empFirmy.getLastName();
                }
            }

            double sredniaPensja = 0;
            if (liczbaPracownikow > 0) {
                sredniaPensja = sumaPensji / liczbaPracownikow;
            }

            CompanyStatistics stats = new CompanyStatistics(liczbaPracownikow, sredniaPensja, najlepiejZarabiajacy);
            statystykiMap.put(nazwaFirmy, stats);
        }

        return statystykiMap;
    }
}