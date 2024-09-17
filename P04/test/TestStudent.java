package test;

import customer.Student;
import product.Media;

public class TestStudent {

    public static void main(String[] args) {

        Student student = new Student("Samridh", 123456789, "samridh@mavs.uta.edu");

        Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0", 5);
        assert student.requestMedia(media).equals("Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 5 points)");

        String studentInfo = student.toString();
        assert studentInfo.equals("Samridh (123456789, samridh@mavs.uta.edu, Account #1)") : "FAIL: Incorrect info";

        try {
            Student invalidStudent = new Student("Nina", 987654321, "nina@gmail.com");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Non-UTA email address: nina@gmail.com") : "FAIL: Invalid email not caught";
        }
    }
}