package test;

import product.Media;
import customer.Account;
import customer.Alacarte;
import customer.Unlimited;

public class TestAccount {

    public static void main(String[] args) {

        Account account1 = new Account();
        
        Account account2 = new Account();

        assert account1.getAccountNumber() == 1 : "FAIL: First account number should be 1";
        assert account2.getAccountNumber() == 2 : "FAIL: Second account number should be 2";

        
        Unlimited unlimitedAccount = new Unlimited();
        Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0", 5);
        assert unlimitedAccount.play(media).equals("Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 5 points)");

       
        Alacarte alacarteAccount = new Alacarte();
        alacarteAccount.buyPoints(10);
        assert alacarteAccount.play(media).equals("Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 5 points)");
        assert alacarteAccount.getPointsRemaining() == 5 : "FAIL: Points remaining should be 5";

        Media anotherMedia = new Media("The Mechanical Monsters", "https://www.youtube.com/watch?v=6LEfzup0aNs", 7);
        assert alacarteAccount.play(anotherMedia).equals("Buy more points: Requires 7 points, you have 5 points");
    }
}