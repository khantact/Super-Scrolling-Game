
import Sounds.getCoin;

public class silverCoin extends Get {
    // Location of image file to be drawn for a RareGet
    private static final String SILVER_IMAGE_FILE = "assets/custom/silverCoin.gif";

    public silverCoin() {
        this(0, 0);
    }

    public silverCoin(int x, int y) {
        super(x, y, SILVER_IMAGE_FILE);
    }

    @Override
    public int getDamageValue() {
        return 0;
    }

    // Creative - returns what sound the object plays on collsion
    @Override
    public void getSound() {
        getCoin.silverCoin();
    }

    @Override
    public int getPointsValue() {
        return 10;
    }
}
