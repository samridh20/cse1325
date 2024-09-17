package customer;

import product.Media;

public abstract class Account {

    private static int nextAccountNumber = 1;
    private int accountNumber;

    public Account() {
        if (nextAccountNumber <= 0) {
            throw new IllegalStateException("Account number invalid");
        }
        this.accountNumber = nextAccountNumber;
        nextAccountNumber++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public abstract String play(Media media);
}