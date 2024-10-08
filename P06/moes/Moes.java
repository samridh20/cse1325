package moes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import product.Media;
import customer.Student;

public class Moes {
    private ArrayList<Media> library = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public void addMedia(Media media) {
        library.add(media);
    }

    public String getMediaList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < library.size(); i++)
            sb.append(i).append(") ").append(library.get(i)).append('\n');
        return sb.toString();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getStudentList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < students.size(); i++)
            sb.append(i).append(") ").append(students.get(i)).append('\n');
        return sb.toString();
    }

    // Save method
    public void save(BufferedWriter bw) throws IOException {
        bw.write(Integer.toString(library.size()) + "\n");
        for (Media media : library) {
            media.save(bw);
        }

        bw.write(Integer.toString(students.size()) + "\n");
        for (Student student : students) {
            student.save(bw);
        }
    }

    // Constructor to load from file
    public Moes(BufferedReader br) throws IOException {
        int mediaCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < mediaCount; i++) {
            library.add(new Media(br));
        }

        int studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student(br));
        }
    }
}