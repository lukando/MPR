import java.util.Objects;

public class Pracownik {

    private String imie;
    private String nazwisko;
    private final String email;
    private String nazwaFirmy;
    private Stanowisko stanowisko;
    private double pensja;

    public Pracownik(String imie, String nazwisko, String email, String nazwaFirmy, Stanowisko stanowisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.nazwaFirmy = nazwaFirmy;
        this.stanowisko = stanowisko;
        this.pensja = stanowisko.getWynagrodzenie();
    }

    public Pracownik(String imie, String nazwisko, String email, String nazwaFirmy, Stanowisko stanowisko, double pensja) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.nazwaFirmy = nazwaFirmy;
        this.stanowisko = stanowisko;
        this.pensja = pensja;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public Stanowisko getStanowisko() {
        return stanowisko;
    }

    public double getPensja() {
        return pensja;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public void setStanowisko(Stanowisko stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                "nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", nazwaFirmy='" + nazwaFirmy + '\'' +
                ", stanowisko=" + stanowisko +
                ", pensja=" + String.format("%.2f", pensja) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pracownik pracownik = (Pracownik) o;
        return Objects.equals(email, pracownik.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}