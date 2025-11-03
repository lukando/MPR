package model;

public enum Position {
    PREZES(25000),
    WICEPREZES(18000),
    MANAGER(12000),
    PROGRAMISTA(8000),
    STAZYSTA(3000);

    private final double bazowaPensja;

    Position(double bazowaPensja) {
        this.bazowaPensja = bazowaPensja;
    }

    public double getBazowaPensja() {
        return bazowaPensja;
    }
}