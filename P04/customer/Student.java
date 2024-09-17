import java.util.regex.Pattern;
import product.Media;

public class Student{

    private static final Pattern EMAIL_PATTERN = Pattern.compile(".+@(?:mavs\\.)?uta\\.edu$");

    private String name;
    private int id;
    private String email;
    private Account account;

    public Student(String name, int id, String email){

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Non-UTA email address: " + email);
        }

        this.name = name;
        this.id = id;
        this.email = email;
        this.account = new Account();
    }

    public String requestMedia(Media media) {
        if (media == null) {
            throw new IllegalArgumentException("Requested media cannot be null");
        }
        return account.play(media);
    }

    @Override
    public String toString() {
        return String.format("%s (%d, %s, Account #%d)",name,id,email,account.getAccountNumber());
    }
}