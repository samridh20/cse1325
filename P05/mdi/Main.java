package mdi;

import moes.Moes;
import product.Media;
import customer.Student;
import java.util.Scanner;

public class Main implements Runnable{
    private Moes moes;
    private String output ="";
    private Menu menu;
    private boolean running =true;

    public Main(){
        moes = new Moes();
        menu = new Menu();
        // Add menu items
        menu.addMenuItem(new MenuItem("Exit", () -> endApp()));
        menu.addMenuItem(new MenuItem("Play media", () -> playMedia()));
        menu.addMenuItem(new MenuItem("List media", () -> listMedia()));
        menu.addMenuItem(new MenuItem("List available points", () -> listAvailablePoints()));
        menu.addMenuItem(new MenuItem("Buy points", () -> buyPoints()));
        menu.addMenuItem(new MenuItem("Add media", () -> addMedia()));
        menu.addMenuItem(new MenuItem("List all students", () -> listStudents()));
        menu.addMenuItem(new MenuItem("Add a student", () -> addStudent()));
    }

    public void addStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Student name? ");
        String name = sc.nextLine();
        System.out.print("Student ID? ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Student email? ");
        String email = sc.nextLine();
        System.out.print("(a)lacarte or (u)nlimited? ");
        boolean unlimited= sc.nextLine().equalsIgnoreCase("u");
        moes.addStudent(new Student(name, id, email, unlimited));
        output = "Added student " + name + " (" + id + ", " + email + ", Account #" + moes.getStudentList() + ")";
    }

    public void listStudents(){
        output =moes.getStudentList();
    }

    public void addMedia(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Title? ");
        String title = sc.nextLine();
        System.out.print("URL? ");
        String url = sc.nextLine();
        System.out.print("Points? ");
        int points =sc.nextInt();
        moes.addMedia(new Media(title, url, points));
        output ="Added media " + title + " (" + url + ", " + points + " points)";
    }

    public void listMedia(){
        output = moes.getMediaList();
    }

    public void playMedia(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Student number? ");
        int studentIndex = sc.nextInt();
        System.out.print("Media number? ");
        int mediaIndex = sc.nextInt();
        output = moes.playMedia(studentIndex, mediaIndex);
    }
    public void listAvailablePoints(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Student number? ");
        int studentIndex = sc.nextInt();
        int points = moes.getPoints(studentIndex);
        output = "Student has " + points + " points.";
    }

    public void buyPoints(){

        Scanner sc = new Scanner(System.in);
        System.out.print("Student number? ");
        int studentIndex = sc.nextInt();
        System.out.print("Current points: " + moes.getPoints(studentIndex) + "\nHow many points to buy? ");
        int points = sc.nextInt();
        if (points > 0) {
            output = moes.buyPoints(studentIndex, points);
        } else {
            output = "Cannot buy negative or zero points!";
        }
    }

    public void endApp(){
        running = false;
    }

    @Override
    public void run(){
        while (running){
            
            if (!output.isEmpty()) {
                System.out.println("\n" + output + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
                output = "";
            }
            System.out.println(menu);
            int selection = Menu.getInt("Selection? ");
            menu.run(selection);
        }
    }

    public static void main(String[] args){
        Main app = new Main();
        app.run();
    }
}
