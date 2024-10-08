package customer;
import product.Media;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Account {
    private int accountNumber;
    private static int nextAccountNumber = 1;

    public Account() {
        this.accountNumber = nextAccountNumber++;
    }

    // Constructor to load from file using BufferedReader
    public Account(BufferedReader br) throws IOException {
        this.accountNumber = Integer.parseInt(br.readLine());
        nextAccountNumber = Integer.parseInt(br.readLine()); // Static field handling
    }

    // Save method to write account details to file
    public void save(BufferedWriter bw) throws IOException {
        bw.write(Integer.toString(accountNumber) + "\n");
        bw.write(Integer.toString(nextAccountNumber) + "\n");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public abstract String play(Media media);
}