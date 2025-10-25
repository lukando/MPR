import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        SerwisPracownikow serwis = new SerwisPracownikow();

        System.out.println("## 1. Dodawanie pracowników...");
        serwis.dodajPracownika(new Pracownik("Anna", "Nowak", "anna.nowak@techcorp.pl", "TechCorp", Stanowisko.PROGRAMISTA));
        serwis.dodajPracownika(new Pracownik("Jan", "Kowalski", "jan.kowalski@techcorp.pl", "TechCorp", Stanowisko.MANAGER));
        serwis.dodajPracownika(new Pracownik("Piotr", "Wiśniewski", "piotr.wisniewski@techcorp.pl", "TechCorp", Stanowisko.PREZES));
        serwis.dodajPracownika(new Pracownik("Zofia", "Dąbrowska", "zofia.dabrowska@techcorp.pl", "TechCorp", Stanowisko.WICEPREZES));
        serwis.dodajPracownika(new Pracownik("Marek", "Lewy", "marek.lewy@techcorp.pl", "TechCorp", Stanowisko.PROGRAMISTA));
        serwis.dodajPracownika(new Pracownik("Ewa", "Bąk", "ewa.bak@innafirma.pl", "InnaFirma", Stanowisko.STAZYSTA));

        serwis.dodajPracownika(new Pracownik("Anna", "Inna", "anna.nowak@techcorp.pl", "TechCorp", Stanowisko.STAZYSTA));
        System.out.println("------------------------------------");


        System.out.println("\n## 2. Lista wszystkich pracowników...");
        serwis.pobierzWszystkichPracownikow().forEach(System.out::println);
        System.out.println("------------------------------------");

        System.out.println("\n## 3.1 Pracownicy w 'TechCorp'...");
        serwis.znajdzPracownikowPoFirmie("TechCorp").forEach(System.out::println);
        System.out.println("------------------------------------");

        System.out.println("\n## 3.2 Pracownicy posortowani wg nazwiska...");
        serwis.pobierzPracownikowPosortowanychPoNazwisku().forEach(System.out::println);
        System.out.println("------------------------------------");

        System.out.println("\n## 3.3 Grupowanie wg stanowiska...");
        Map<Stanowisko, List<Pracownik>> pogrupowaniPoStanowisku = serwis.grupujPracownikowPoStanowisku();
        pogrupowaniPoStanowisku.forEach((stanowisko, listaPracownikow) -> {
            System.out.println("Stanowisko: " + stanowisko);
            listaPracownikow.forEach(prac -> System.out.println("  -> " + prac.getImie() + " " + prac.getNazwisko()));
        });
        System.out.println("------------------------------------");

        System.out.println("\n## 3.4 Liczba pracowników na stanowisku...");
        Map<Stanowisko, Long> liczbaNaStanowisku = serwis.liczPracownikowPoStanowisku();
        liczbaNaStanowisku.forEach((stanowisko, liczba) ->
                System.out.println(stanowisko + ": " + liczba + " pracownik(ów)")
        );
        System.out.println("------------------------------------");

        System.out.println("\n## 4.1 Średnie wynagrodzenie w firmie...");
        System.out.printf("Średnia pensja: %.2f PLN%n", serwis.obliczSredniaPensje());
        System.out.println("------------------------------------");

        System.out.println("\n## 4.2 Pracownik z najwyższym wynagrodzeniem...");
        Optional<Pracownik> najlepiejZarabiajacy = serwis.pobierzPracownikaZNajwyzszaPensja();
        najlepiejZarabiajacy.ifPresent(pracownik ->
                System.out.println("Najlepiej zarabia: " + pracownik)
        );
        System.out.println("------------------------------------");
    }
}