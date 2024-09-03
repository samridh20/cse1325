import java.util.HashMap;
import java.util.Map;

public class Coin {
    private static final Map<Denomination, Double> values = new HashMap<>();
    static {
        values.put(Denomination.PENNY, 0.01);
        values.put(Denomination.NICKEL, 0.05);
        values.put(Denomination.DIME, 0.10);
        values.put(Denomination.QUARTER, 0.25);
    }

    private Denomination denomination;
    private int year;

    public Coin(Denomination denomination, int year) {
        this.denomination = denomination;
        this.year = year;
    }

    public double getValue() {
        return values.get(denomination);
    }

    public int getYear() {
        return year;
    }

    public double getWeight() {
        switch (denomination) {
            case PENNY:
                return (year < 1983) ? 3.110 : 2.500;
            case NICKEL:
                return 5.000;
            case DIME:
                return (year < 1965) ? 2.500 : 2.268;
            case QUARTER:
                return (year < 1965) ? 6.250 : 5.670;
            default:
                return 0.0;
        }
    }

    @Override
    public String toString() {
        return year + " " + denomination;
    }
}