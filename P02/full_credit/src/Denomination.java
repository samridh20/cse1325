public enum Denomination {
    PENNY, NICKEL, DIME, QUARTER;

    public double getValue() {
        return switch (this) {
            case PENNY -> 0.01;
            case NICKEL -> 0.05;
            case DIME -> 0.10;
            case QUARTER -> 0.25;
        };
    }
}