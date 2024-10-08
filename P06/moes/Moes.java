package moes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import product.Media;
import customer.Student;
import customer.Alacarte;
import customer.Unlimited;
import customer.Account;

public class Moes {

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Media> mediaList = new ArrayList<>();

    // No-argument constructor (for non-file I/O cases)
    public Moes() {
        this.students = new ArrayList<>();
        this.mediaList = new ArrayList<>();
    }

    // Constructor for file I/O using BufferedReader
    public Moes(BufferedReader br) throws IOException {
        this();
        // Read the file and populate the lists
        int numStudents = Integer.parseInt(br.readLine());
        for (int i = 0; i < numStudents; i++) {
            students.add(new Student(br)); // Assuming Student has a constructor that takes BufferedReader
        }

        int numMedia = Integer.parseInt(br.readLine());
        for (int i = 0; i < numMedia; i++) {
            mediaList.add(new Media(br)); // Assuming Media has a constructor that takes BufferedReader
        }
    }

    // Save the current state to a BufferedWriter
    public void save(BufferedWriter bw) throws IOException {
        bw.write(students.size() + "\n");
        for (Student student : students) {
            student.save(bw);  // Assuming Student has a save method
        }

        bw.write(mediaList.size() + "\n");
        for (Media media : mediaList) {
            media.save(bw);  // Assuming Media has a save method
        }
    }

    // Method to play media by a student
    public String playMedia(int studentIndex, int mediaIndex) {
        Student student = students.get(studentIndex);
        Media media = mediaList.get(mediaIndex);
        return student.requestMedia(media);
    }

    // Method to get points for a student
    public int getPoints(int studentIndex) {
        Account account = students.get(studentIndex).getAccount();
        if (account instanceof Alacarte) {
            return ((Alacarte) account).getPointsRemaining();
        } else if (account instanceof Unlimited) {
            return Integer.MAX_VALUE;
        } else {
            throw new UnsupportedOperationException("Unknown account type");
        }
    }

    // Method to buy points for a student
    public String buyPoints(int studentIndex, int points) {
        Account account = students.get(studentIndex).getAccount();
        if (account instanceof Alacarte) {
            Alacarte alacarte = (Alacarte) account;
            alacarte.buyPoints(points);
            return students.get(studentIndex).toString() + " now has " + alacarte.getPointsRemaining() + " points.";
        } else if (account instanceof Unlimited) {
            return students.get(studentIndex).toString() + " has an unlimited account.";
        } else {
            throw new UnsupportedOperationException("Unknown account type");
        }
    }

    // Add a student to the list
    public void addStudent(Student student) {
        students.add(student);
    }

    // Add media to the media list
    public void addMedia(Media media) {
        mediaList.add(media);
    }

    // Method to get the list of students
    public String getStudentList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < students.size(); i++) {
            sb.append(i).append(") ").append(students.get(i)).append("\n");
        }
        return sb.toString();
    }

    // Method to get the list of media
    public String getMediaList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mediaList.size(); i++) {
            sb.append(i).append(") ").append(mediaList.get(i)).append("\n");
        }
        return sb.toString();
    }
}