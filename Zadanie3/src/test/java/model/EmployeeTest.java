package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void testEqualsIHashCodeDzialajaWylacznieNaEmailu() {
        Employee emp1 = new Employee("Jan", "Kowalski", "jan.kowalski@test.pl", "FirmaA", Position.PROGRAMISTA, 8000);
        Employee emp2 = new Employee("Anna", "Nowak", "jan.kowalski@test.pl", "FirmaB", Position.MANAGER, 12000);
        Employee emp3 = new Employee("Piotr", "Zieliński", "piotr.zielinski@test.pl", "FirmaA", Position.PROGRAMISTA, 8000);

        assertTrue("Pracownicy z tym samym emailem powinni być równi (equals)", emp1.equals(emp2));
        assertTrue("Metoda equals musi być symetryczna", emp2.equals(emp1));

        assertEquals("Pracownicy z tym samym emailem powinni mieć ten sam hashCode", emp1.hashCode(), emp2.hashCode());

        assertFalse("Pracownicy z różnymi emailami nie powinni być równi", emp1.equals(emp3));

        assertFalse("Porównanie z null powinno zawsze zwracać false", emp1.equals(null));
    }
}