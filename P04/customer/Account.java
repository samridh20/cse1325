import product.Media;

public class Account{

    private static int nextAccountNumber = 1;
    private int accountNumber;

    public Account(){

        if (nextAccountNumber <= 0){
            throw new IllegalStateException("Account number invalid");
        }

        this.accountNumber = nextAccountNumber;
        nextAccountNumber++;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public String play(Media media){

        if (media == null){
            throw new IllegalArgumentException("Media is null");
        }
        return "Playing " + media.toString();
    }
}