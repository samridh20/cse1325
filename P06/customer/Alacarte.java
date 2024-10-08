package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Media;

public class Alacarte extends Account {
    private int pointsRemaining = 0;

    public Alacarte() {
        super();
    }

    // Constructor to load from file
    public Alacarte(BufferedReader br) throws IOException {
        super(br);
        this.pointsRemaining = Integer.parseInt(br.readLine());
    }

    // Save method
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(Integer.toString(pointsRemaining) + "\n");
    }

    public void buyPoints(int points) {
        pointsRemaining += points;
    }

    public int getPointsRemaining() {
        return pointsRemaining;
    }

    @Override
    public String play(Media media) {
        int points = media.getPoints();
        if (points > pointsRemaining)
            return "Buy more points: Requires " + points + " points, you have " + pointsRemaining;
        pointsRemaining -= points;
        return "Playing " + media;
    }
}