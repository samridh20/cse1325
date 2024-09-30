package moes;

import product.Media;

import customer.Student;

import customer.Account;
import customer.Alacarte;
import customer.Unlimited;

import java.util.ArrayList;

public class Moes {
    public void addMedia(Media media) {
        library.add(media);
    }
    public String getMediaList() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<library.size(); ++i)
            sb.append("" + i + ") " + library.get(i) + '\n');
        return sb.toString();
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public String getStudentList() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<students.size(); ++i)
            sb.append("" + i + ") " + students.get(i) + '\n');
        return sb.toString();
    }
    public int getPoints(int studentIndex) {
        Account account = students.get(studentIndex).getAccount();
        if(account instanceof Alacarte) {
            return ((Alacarte) account).getPointsRemaining();
        } else if (account instanceof Unlimited) {
            return Integer.MAX_VALUE;
        } else {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }

    }
    public String buyPoints(int studentIndex, int points) {
        Student student = students.get(studentIndex);
        Account account = student.getAccount();
        if(account instanceof Alacarte) {
            Alacarte alacarte = (Alacarte) account;
            alacarte.buyPoints(points);
            return student.toString() + " now has "
                 + alacarte.getPointsRemaining() + " points";
        } else if (account instanceof Unlimited) {
            return student.toString() 
                 + " has unlimited account and needs no points!";
        } else {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }
    public String playMedia(int studentIndex, int mediaIndex) {
        Student student = students.get(studentIndex);
        Media media = library.get(mediaIndex);
        return student.requestMedia(media);
    }
    private ArrayList<Media> library = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
}
