package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Models a media product to serve to the students
 *
 * @author             Samridh Singh
 * @version            1.1
 * @since              1.0
 */
public class Media {
    private String title;
    private String url;
    private int points;

    /**
     * Creates a Media instance.
     *
     * @param title     the name by which the media is known
     * @param url       the Uniform Resource Locator of the media
     * @param points    the cost for accessing the media
     * @since           1.0
     */
    public Media(String title, String url, int points) {
        this.title = title;
        this.url = url;
        this.points = points;

        // Validate URL format
        try {
            new java.net.URI(url).toURL();
        } catch (Exception e) {
            throw new RuntimeException(url + " is invalid", e);
        }
    }

    /**
     * Creates a Media instance from a file using a BufferedReader.
     *
     * @param br BufferedReader to read media data
     * @throws IOException if an I/O error occurs
     */
    public Media(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.url = br.readLine();
        this.points = Integer.parseInt(br.readLine());
    }

    /**
     * Saves the media data to a file using a BufferedWriter.
     *
     * @param bw BufferedWriter to write media data
     * @throws IOException if an I/O error occurs
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + "\n");
        bw.write(url + "\n");
        bw.write(Integer.toString(points) + "\n");
    }

    /**
     * Returns the number of points required to access this media.
     *
     * @return the cost in points 
     * @since 1.0
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns a media summary in receipt format.
     *
     * @return the title with parenthetical url and points
     * @since 1.0
     */
    @Override
    public String toString() {
        return title + " (" + url + ", " + points + " points)";
    }
}