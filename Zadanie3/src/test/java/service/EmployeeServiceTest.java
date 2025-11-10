package service;

import model.CompanyStatistics;
import model.Employee;
import model.Position;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;


public class EmployeeServiceTest {

    private EmployeeService service;

    private Employee emp1_manager_A;
    private Employee emp2_programista_A;
    private Employee emp3_stazysta_B;
    private Employee emp4_prezes_A;

    @Before
    public void setUp() {
        service = new EmployeeService();

        emp1_manager_A = new Employee("Jan", "Kowalski", "jan.kowalski@firma.pl", "FirmaA", Position.MANAGER, 12000);
        emp2_programista_A = new Employee("Anna", "Nowak", "anna.nowak@firma.pl", "FirmaA", Position.PROGRAMISTA, 9000);
        emp3_stazysta_B = new Employee("Piotr", "Zieliński", "piotr.zielinski@firma.pl", "FirmaB", Position.STAZYSTA, 3000);
        emp4_prezes_A = new Employee("Zofia", "Bąk", "zofia.bak@firma.pl", "FirmaA", Position.PREZES, 25000);
    }


    @Test
    public void testAddEmployeeSukces() {

        boolean result = service.addEmployee(emp1_manager_A);


        assertTrue("Dodanie unikalnego pracownika powinno zwrócić true", result);
        assertEquals("Lista powinna zawierać jednego pracownika", 1, service.getAllEmployees().size());
        assertEquals("jan.kowalski@firma.pl", service.getAllEmployees().get(0).getEmail());
    }

    @Test
    public void testAddEmployeeDuplikatEmail() {

        service.addEmployee(emp1_manager_A);
        Employee duplikat = new Employee("Inny", "Człowiek", "jan.kowalski@firma.pl", "InnaFirma", Position.STAZYSTA, 3000);

        boolean result = service.addEmployee(duplikat);

        assertFalse("Próba dodania duplikatu email powinna zwrócić false", result);
        assertEquals("Lista nadal powinna mieć tylko jednego pracownika", 1, service.getAllEmployees().size());
    }


    @Test
    public void testValidateSalaryConsistency() {
        Employee niespojnyMenadzer = new Employee("Marek", "Błąd", "marek.blad@firma.pl", "FirmaA", Position.MANAGER, 10000);
        service.addEmployee(niespojnyMenadzer);
        service.addEmployee(emp2_programista_A);
        service.addEmployee(emp3_stazysta_B);

        List<Employee> niespojni = service.validateSalaryConsistency();

        assertEquals("Powinien zostać znaleziony jeden niespójny pracownik", 1, niespojni.size());
        assertEquals("marek.blad@firma.pl", niespojni.get(0).getEmail());
    }

    @Test
    public void testGetCompanyStatistics() {
        service.addEmployee(emp1_manager_A);
        service.addEmployee(emp2_programista_A);
        service.addEmployee(emp3_stazysta_B);
        service.addEmployee(emp4_prezes_A);

        Map<String, CompanyStatistics> statsMap = service.getCompanyStatistics();

        assertEquals("Powinny być statystyki dla dwóch firm", 2, statsMap.size());
        assertTrue(statsMap.containsKey("FirmaA"));
        assertTrue(statsMap.containsKey("FirmaB"));

        CompanyStatistics statsA = statsMap.get("FirmaA");
        double oczekiwanaSredniaA = (12000 + 9000 + 25000) / 3.0; // 15333.33...
        String oczekiwanyStringA = "CompanyStatistics{LiczbaPracownikow=3, SredniaPensja=15333,33, NajlepiejZarabiajacy='Zofia Bąk'}";

        String actualStringA = statsA.toString().replaceAll("\\.0", ",0");
        assertEquals(oczekiwanyStringA, actualStringA);

        CompanyStatistics statsB = statsMap.get("FirmaB");
        String oczekiwanyStringB = "CompanyStatistics{LiczbaPracownikow=1, SredniaPensja=3000,00, NajlepiejZarabiajacy='Piotr Zieliński'}";
        assertEquals(oczekiwanyStringB, statsB.toString());
    }


    @Test
    public void testGetCompanyStatisticsPustaLista() {

        Map<String, CompanyStatistics> statsMap = service.getCompanyStatistics();

        assertNotNull("Mapa nie powinna być nullem", statsMap);
        assertTrue("Mapa powinna być pusta, gdy nie ma pracowników", statsMap.isEmpty());
    }

    @Test
    public void testValidateSalaryConsistencyPustaLista() {

        List<Employee> niespojni = service.validateSalaryConsistency();

        assertNotNull("Lista nie powinna być nullem", niespojni);
        assertTrue("Lista powinna być pusta, gdy nie ma pracowników", niespojni.isEmpty());
    }
}