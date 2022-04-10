import Sounds.getCoin;

public class silverCoin extends Get {
    // Location of image file to be drawn for a RareGet
    private static final String RAREGET_IMAGE_FILE = "assets/custom/silverCoin.gif";

    public silverCoin() {
        this(0, 0);
    }

    public silverCoin(int x, int y) {
        super(x, y, RAREGET_IMAGE_FILE);
    }

    @Override
    public int getDamageValue() {
        return 1;
    }

    // Creative
    @Override
    public void getSound() {
        getCoin.silverCoin();
    }
}
