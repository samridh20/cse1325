package mdi;

import moes.Moes;
import product.Media;    
import customer.Student;
import java.io.*;
import java.util.Scanner;

public class Main implements Runnable{
    private Moes moes;
    private String output = "";
    private Menu menu;
    private boolean running = true;
    private boolean dirty = false;


    private static final String magicCookie = "MOESFILE";
    private static final String fileVersion = "1.0";
    private static final String extension = ".moes";
    private String filename = "";

    public Main(){
        moes = new Moes();  // No-argument constructor works now
        menu = new Menu();

        // Add menu items
        menu.addMenuItem(new MenuItem("Play media", () -> playMedia()));
        menu.addMenuItem(new MenuItem("List media", () -> listMedia()));
        menu.addMenuItem(new MenuItem("List available points", () -> listAvailablePoints()));
        menu.addMenuItem(new MenuItem("Buy points", () -> buyPoints()));
        menu.addMenuItem(new MenuItem("Add media", () -> addMedia()));
        menu.addMenuItem(new MenuItem("List all students", () -> listStudents()));
        menu.addMenuItem(new MenuItem("Add a student", () -> addStudent()));
        menu.addMenuItem(new MenuItem("New MOES file", () -> newMoes()));
        menu.addMenuItem(new MenuItem("Open file", () -> open()));
        menu.addMenuItem(new MenuItem("Save to file", () -> save()));
        menu.addMenuItem(new MenuItem("Save as new file", () -> saveAs()));
        menu.addMenuItem(new MenuItem("Exit", () -> endApp()));
    }
    private void checkDirty() {
        if (dirty) {
            Scanner sc = new Scanner(System.in);
            System.out.println("You have unsaved changes. Would you like to:");
            System.out.println("(s)ave, save (a)s new file, (d)iscard changes, or (c)ancel?");
            String choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "s":
                    save(); // Save to the current file
                    break;
                case "a":
                    saveAs(); // Save to a new file
                    break;
                case "d":
                    // Discard changes
                    dirty = false;
                    output = "Unsaved changes discarded.";
                    break;
                case "c":
                    // Cancel the command
                    throw new IllegalStateException("Operation canceled by user.");
                default:
                    System.out.println("Invalid choice, returning to the main menu.");
                    break;
            }
        }
    }

    public void newMoes(){

        checkDirty();
        moes = new Moes();
        filename = "";
        dirty = false; 
        output = "New MOES instance created.";
    }

    public void save(){
        if (filename.isEmpty()) {
            output = "No file loaded. Use 'Save as new file' instead.";
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(magicCookie + "\n");
            bw.write(fileVersion + "\n");
            moes.save(bw);
            dirty = false;
            output = "MOES data saved to " + filename;
        } catch (IOException e) {
            output = "Error saving to file: " + e.getMessage();
        }
    }
    public void saveAs() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new filename: ");
        String newFilename = sc.nextLine();
        if (newFilename.isEmpty()) {
            output = "Command canceled.";
            return;
        }
        if (!newFilename.endsWith(extension)) {
            newFilename += extension;
        }
        filename = newFilename;
        save();
    }
    public void open(){
        checkDirty();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename to open: ");
        String newFilename = sc.nextLine();
        if (newFilename.isEmpty()) {
            output = "Command canceled.";
            return;
        }
        if (!newFilename.endsWith(extension)) {
            newFilename += extension;
        }

        try (BufferedReader br =new BufferedReader(new FileReader(newFilename))) {
            String magic = br.readLine();
            String version = br.readLine();
            if (!magic.equals(magicCookie) || !version.equals(fileVersion)) {
                throw new IOException("Invalid file format or version.");
            }
            moes = new Moes(br); // Load Moes from file
            filename = newFilename;
            dirty = false;
            output = "MOES data loaded from " + newFilename;
        } catch (IOException e) {
            output = "Error opening file: " + e.getMessage();
        }
    }

    // Add student method
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
        boolean unlimited = sc.nextLine().equalsIgnoreCase("u");
        moes.addStudent(new Student(name, id, email, unlimited));
        dirty = true;
        output = "Added student " + name;
    }

    public void listStudents(){
        output = moes.getStudentList();
    }

    public void addMedia() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Title? ");
        String title = sc.nextLine();
        if (title.isEmpty()) {
            output = "Command canceled.";
            return;
        }
        System.out.print("URL? ");
        String url = sc.nextLine();
        if (url.isEmpty()) {
            output = "Command canceled.";
            return;
        }
        System.out.print("Points? ");
        int points = sc.nextInt();
        
        moes.addMedia(new Media(title,url,points));
        dirty = true; 
        output ="Added media " + title;
    }

    public void listMedia(){
        output =moes.getMediaList();
    }

    public void playMedia(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Student number? ");
        int studentIndex =sc.nextInt();
        System.out.print("Media number? ");
        int mediaIndex =sc.nextInt();
        output = moes.playMedia(studentIndex, mediaIndex);
    }

    public void listAvailablePoints(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Student number? ");
        int studentIndex = sc.nextInt();
        int points =moes.getPoints(studentIndex);
        output ="Student has " + points + " points.";
    }
    public void buyPoints() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Student number? ");
        int studentIndex = sc.nextInt();
        System.out.print("Current points: " + moes.getPoints(studentIndex) + "\nHow many points to buy? ");
        int points = sc.nextInt();
        if (points > 0) {
            output =moes.buyPoints(studentIndex, points);
        } else {
            output = "Cannot buy negative or zero points!";
        }
    }

    public void endApp(){

        running = false;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (running) {
            if (!output.isEmpty()) {
                System.out.println("\n" + output + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
                output = "";
            }
            System.out.println(menu);
            System.out.print("Selection? ");
            String input = sc.nextLine();  // Read the input as a string
    
            if (input.isEmpty()) {
                // Exit the program if Enter is pressed without input
                System.out.println("Exiting program.");
                endApp();
                return;
            }
    
            // Convert the input to an integer if it's not empty
            try {
                int selection = Integer.parseInt(input);
                menu.run(selection);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
   
}