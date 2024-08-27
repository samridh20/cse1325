import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        System.out.println("What is your name:");
        Scanner getName = new Scanner(System.in);

        String name = getName.nextLine();

        System.out.println("Hello " + name );
    }
}