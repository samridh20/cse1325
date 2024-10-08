package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Media;

public class Student {
    private String name;
    private int id;
    private String email;
    private Account account;

    public Student(String name, int id, String email, boolean unlimited) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.account = unlimited ? new Unlimited() : new Alacarte();
    }

    // Constructor to load from file
    public Student(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.id = Integer.parseInt(br.readLine());
        this.email = br.readLine();
        String accountType = br.readLine();
        if (accountType.equals("Unlimited")) {
            this.account = new Unlimited(br);
        } else {
            this.account = new Alacarte(br);
        }
    }

    // Save method
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + "\n");
        bw.write(Integer.toString(id) + "\n");
        bw.write(email + "\n");
        bw.write(account.getClass().getSimpleName() + "\n");
        account.save(bw);
    }

    public Account getAccount() {
        return account;
    }

    public String requestMedia(Media media) {
        return account.play(media);
    }

    @Override
    public String toString() {
        return name + " (" + id + ", " + email + ", Account #" + account.getAccountNumber() + ")";
    }
}