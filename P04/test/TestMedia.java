package test;

import product.Media;

public class TestMedia {

    public static void main(String[] args) {
        
        Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0", 5);
        String expected = "The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 5 points)";
        String result = media.toString();

        assert expected.equals(result) : String.format("FAIL: Expected '%s', but got '%s'", expected, result);
    }
}