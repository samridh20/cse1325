package customer;
import product.Media;
public class Alacarte extends Account {

    private int pointsRemaining;

    public void buyPoints(int points) {
        pointsRemaining += points;
    }

    public int getPointsRemaining() {
        return pointsRemaining;
    }

    @Override
    public String play(Media media) {
        if (media == null) {
            throw new IllegalArgumentException("Media is null");
        }
        if (pointsRemaining >= media.getPoints()) {
            pointsRemaining -= media.getPoints();
            return "Playing " + media.toString();
        } else {
            return String.format("Buy more points: Requires %d points, you have %d points", media.getPoints(), pointsRemaining);
        }
    }
}