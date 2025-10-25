import java.util.*;
import java.util.stream.Collectors;

public class SerwisPracownikow {
    private final List<Pracownik> pracownicy = new ArrayList<>();

    public void dodajPracownika(Pracownik pracownik) {
        boolean emailIstnieje = pracownicy.stream()
                .anyMatch(p -> p.getEmail().equals(pracownik.getEmail()));

        if (emailIstnieje) {
            System.err.println("Błąd: Pracownik z adresem email '" + pracownik.getEmail() + "' już istnieje.");
            return;
        }

        pracownicy.add(pracownik);
        System.out.println("Dodano pracownika: " + pracownik.getImie() + " " + pracownik.getNazwisko());
    }

    public List<Pracownik> pobierzWszystkichPracownikow() {
        return new ArrayList<>(pracownicy);
    }

    public List<Pracownik> znajdzPracownikowPoFirmie(String nazwaFirmy) {
        return pracownicy.stream()
                .filter(p -> p.getNazwaFirmy().equalsIgnoreCase(nazwaFirmy))
                .collect(Collectors.toList());
    }

    public List<Pracownik> pobierzPracownikowPosortowanychPoNazwisku() {
        Comparator<Pracownik> wgNazwiska = Comparator.comparing(Pracownik::getNazwisko);
        return pracownicy.stream()
                .sorted(wgNazwiska)
                .collect(Collectors.toList());
    }

    public Map<Stanowisko, List<Pracownik>> grupujPracownikowPoStanowisku() {
        return pracownicy.stream()
                .collect(Collectors.groupingBy(Pracownik::getStanowisko));
    }

    public Map<Stanowisko, Long> liczPracownikowPoStanowisku() {
        return pracownicy.stream()
                .collect(Collectors.groupingBy(Pracownik::getStanowisko, Collectors.counting()));
    }

    public double obliczSredniaPensje() {
        return pracownicy.stream()
                .mapToDouble(Pracownik::getPensja)
                .average()
                .orElse(0.0);
    }

    public Optional<Pracownik> pobierzPracownikaZNajwyzszaPensja() {
        return pracownicy.stream()
                .max(Comparator.comparingDouble(Pracownik::getPensja));
    }
}