package customer;
import product.Media;
public class Unlimited extends Account {

    @Override
    public String play(Media media) {
        if (media == null) {
            throw new IllegalArgumentException("Media is null");
        }
        return "Playing " + media.toString();
    }
}