import Sounds.getCoin;

public class copperCoin extends Get {
    private static final String COPPER_IMAGE_FILE = "assets/custom/copperCoin.gif";

    public copperCoin() {
        this(0, 0);
    }

    public copperCoin(int x, int y) {
        super(x, y, COPPER_IMAGE_FILE);
    }

    @Override
    public int getDamageValue() {
        return 0;
    }

    // Creative
    @Override
    public void getSound() {
        getCoin.copperCoin();
    }

    @Override
    public int getPointsValue() {
        return 5;
    }
}
