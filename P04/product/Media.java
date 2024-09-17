public class Media {

    private String title;
    private String url;
    private int points;

    public Media(String title, String url, int points) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Media title is empty");
        }

        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is empty");
        }

        this.title = title;
        this.url = url;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d points)", title, url, points);
    }
}