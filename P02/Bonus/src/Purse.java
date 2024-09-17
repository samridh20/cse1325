public class Purse {
    public static void main(String[] args) {
        Coin[] coins = {
            new Coin(Denomination.PENNY, 1910),
            new Coin(Denomination.NICKEL, 1945),
            new Coin(Denomination.DIME, 1970),
            new Coin(Denomination.QUARTER, 1980),
            new Coin(Denomination.PENNY, 2010)
        };

        double totalValue = 0;
        double totalWeight = 0;
        int earliestYear = Integer.MAX_VALUE;
        int latestYear = Integer.MIN_VALUE;
    

        for (Coin coin : coins) {
            totalValue += coin.getValue();
            totalWeight += coin.getWeight();
            earliestYear = Math.min(earliestYear, coin.getYear());
            latestYear = Math.max(latestYear, coin.getYear());
            System.out.println(coin);
        }

        System.out.printf("You have $ %.2f in coins between %d and %d weighing %.3f grams\n", totalValue, earliestYear, latestYear, totalWeight);
    }
}