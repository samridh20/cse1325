package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Media;

public class Unlimited extends Account {

    public Unlimited() {
        super();
    }

    // Constructor to load from file
    public Unlimited(BufferedReader br) throws IOException {
        super(br);
    }

    // Save method
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
    }

    @Override
    public String play(Media media) {
        return "Playing " + media;
    }
}