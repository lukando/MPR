package model;

public class CompanyStatistics {
    private final int employeeCount;
    private final double averageSalary;
    private final String highestPaidEmployeeName;

    public CompanyStatistics(int employeeCount, double averageSalary, String highestPaidEmployeeName) {
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
        this.highestPaidEmployeeName = highestPaidEmployeeName;
    }

    @Override
    public String toString() {
        return "CompanyStatistics{" +
                "LiczbaPracownikow=" + employeeCount +
                ", SredniaPensja=" + String.format("%.2f", averageSalary) +
                ", NajlepiejZarabiajacy='" + highestPaidEmployeeName + '\'' +
                '}';
    }
}