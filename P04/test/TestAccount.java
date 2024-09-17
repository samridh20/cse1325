public class TestAccount {

    public static void main(String[] args) {

        Account account1 = new Account();
        Account account2 = new Account();

        assert account1.getAccountNumber() == 1 : "FAIL:First account number should be 1 not " + account1.getAccountNumber();
        assert account2.getAccountNumber() == 2 : "FAIL:Second account number should be 2 not" + account2.getAccountNumber();
    }
}