public class Purse {
    public static void main(String[] args) {
        Coin[] coins = {
            new Coin(Denomination.PENNY, 1909),
            new Coin(Denomination.NICKEL, 1939),
            new Coin(Denomination.DIME, 1946),
            new Coin(Denomination.QUARTER, 1964),
            new Coin(Denomination.PENNY, 2014)
        };

        double totalValue = 0;
        int earliestYear = Integer.MAX_VALUE;
        int latestYear = Integer.MIN_VALUE;

        for (Coin coin : coins) {
            totalValue += coin.getValue();
            earliestYear = Math.min(earliestYear, coin.getYear());
            latestYear = Math.max(latestYear, coin.getYear());
        }

        System.out.printf("You have $ %.2f in coins between %d and %d\n", totalValue, earliestYear, latestYear);
    }
}