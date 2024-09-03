public class Coin {
    private Denomination denomination;
    private int year;

    public Coin(Denomination denomination, int year) {
        this.denomination = denomination;
        this.year = year;
    }

    public double getValue() {
        return denomination.getValue();
    }

    public int getYear() {
        return year;
    }

    public double getWeight() {
        return switch (denomination) {
            case PENNY -> (year < 1983) ? 3.110 : 2.500;
            case NICKEL -> 5.000;
            case DIME -> (year < 1965) ? 2.500 : 2.268;
            case QUARTER -> (year < 1965) ? 6.250 : 5.670;
        };
    }

    
}