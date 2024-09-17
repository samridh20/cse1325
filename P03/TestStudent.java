public class TestStudent {
    public static void main(String[] args) {
        int failureCount = 0; 

        
        try {
            Student student1 = new Student("John Doe", 123456789, "johndoe@mavs.uta.edu");
            String expectedToString = "John Doe (123456789, johndoe@mavs.uta.edu, Account #1)";
            String actualToString = student1.toString();
            
            if (!expectedToString.equals(actualToString)) {
                System.out.println("FAIL: Expected \"" + expectedToString + "\", but got \"" + actualToString + "\"");
                failureCount |= 0b001; 
            }
        } catch (Exception e) {
            System.out.println("FAIL: Unexpected exception in toString test.");
            failureCount |= 0b001; 
        }

        
        try {
            Student student2 = new Student("Jane Doe", 987654321, "janedoe@gmail.com");
            System.out.println("FAIL: Expected IllegalArgumentException for non-UTA email, but no exception was thrown.");
            failureCount |= 0b010; 
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Non-UTA email address: janedoe@gmail.com";
            if (!e.getMessage().equals(expectedMessage)) {
                System.out.println("FAIL: Expected \"" + expectedMessage + "\", but got \"" + e.getMessage() + "\"");
                failureCount |= 0b010; 
        } catch (Exception e) {
            System.out.println("FAIL: Expected IllegalArgumentException, but got a different exception.");
            failureCount |= 0b010; 
        }

        
        try {
            Student student3 = new Student("Jake Smith", 112233445, "jake@mavs.uta.edu");
            Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0");
            String expectedPlayResult = "Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0)";
            String actualPlayResult = student3.requestMedia(media);

            if (!expectedPlayResult.equals(actualPlayResult)) {
                System.out.println("FAIL: Expected \"" + expectedPlayResult + "\", but got \"" + actualPlayResult + "\"");
                failureCount |= 0b100; 
            }
        } catch (Exception e) {
            System.out.println("FAIL: Unexpected exception in requestMedia test.");
            failureCount |= 0b100; 
        }

        
        System.exit(failureCount);
    }
}