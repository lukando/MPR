public enum Stanowisko {
    PREZES("Prezes", 25000, 5),
    WICEPREZES("Wiceprezes", 18000, 4),
    MANAGER("Manager", 12000, 3),
    PROGRAMISTA("Programista", 8000, 2),
    STAZYSTA("Stażysta", 3000, 1);

    private final String nazwaPrzyjazna;
    private final double wynagrodzenie; // Zmieniono nazwę pola
    private final int poziomHierarchii;

    Stanowisko(String nazwaPrzyjazna, double wynagrodzenie, int poziomHierarchii) {
        this.nazwaPrzyjazna = nazwaPrzyjazna;
        this.wynagrodzenie = wynagrodzenie;
        this.poziomHierarchii = poziomHierarchii;
    }
    public double getWynagrodzenie() {
        return wynagrodzenie;
    }
    public int getPoziomHierarchii() {
        return poziomHierarchii;
    }

    @Override
    public String toString() {
        return this.nazwaPrzyjazna;
    }
}