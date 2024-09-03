public enum Denomination {
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25);

    private final double value;

    Denomination(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}