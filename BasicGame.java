import java.awt.*;
import java.awt.event.*;
import java.util.*;

//A Basic version of the scrolling game, featuring Avoids, Gets, and RareGets
//Players must reach a score threshold to win
//If player runs out of HP (via too many Avoid collisions) they lose
public class BasicGame extends AbstractGame {

    // Dimensions of game window
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 600;

    // Starting Player coordinates
    private static final int STARTING_PLAYER_X = 0;
    private static final int STARTING_PLAYER_Y = 100;

    // Score needed to win the game
    private static final int SCORE_TO_WIN = 300;

    // Maximum that the game speed can be increased to
    // (a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    private static final int MAX_GAME_SPEED = 300;
    // Interval that the speed changes when pressing speed up/down keys
    private static final int SPEED_CHANGE = 20;

    private static final String INTRO_SPLASH_FILE = "assets/splash.gif";
    // Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;

    // Interval that Entities get spawned in the game window
    // ie: once every how many ticks does the game attempt to spawn new Entities
    private static final int SPAWN_INTERVAL = 45;
    // Maximum Entities that can be spawned on a single call to spawnEntities
    private static final int MAX_SPAWNS = 3;

    // A Random object for all your random number generation needs!
    public static final Random rand = new Random();

    // Player's current score
    private int score;

    // Stores a reference to game's Player object for quick reference
    // (This Player will also be in the displayList)
    private Player player;
    private Avoid avoid;
    private Get get;
    private RareGet rget;

    // Didnt see the declarations in AbstractGame sorry
    public static final int rightArrow = KeyEvent.VK_RIGHT;
    public static final int leftArrow = KeyEvent.VK_LEFT;
    public static final int upArrow = KeyEvent.VK_UP;
    public static final int downArrow = KeyEvent.VK_DOWN;

    public BasicGame() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public BasicGame(int gameWidth, int gameHeight) {
        super(gameWidth, gameHeight);
    }

    // Performs all of the initialization operations that need to be done before the
    // game starts
    protected void preGame() {
        this.setSplashImage(INTRO_SPLASH_FILE);
        this.setBackgroundColor(Color.BLACK);
        player = new Player(STARTING_PLAYER_X, STARTING_PLAYER_Y);
        displayList.add(player);
        score = 0;
    }

    // Called on each game tick
    protected void updateGame() {
        // scroll all scrollable Entities on the game board
        // Spawn new entities only at a certain interval
        scrollEntities();
        if (ticksElapsed % SPAWN_INTERVAL == 0) {
            spawnEntities();
        }
        // Update the title text on the top of the window
        setTitleText("HP: " + player.getHP() + " Score: " + score);

    }

    // Scroll all scrollable entities per their respective scroll speeds
    protected void scrollEntities() {
        for (int i = 1; i < displayList.size(); i++) {
            Entity obj = displayList.get(i);
            if (obj instanceof Scrollable) {
                ((Scrollable) obj).scroll();
            }
            if (obj.getX() < 0 - obj.getWidth()) {
                displayList.remove(obj);
            }
            if (obj instanceof Consumable) {
                if (player.isCollidingWith(obj) == true) {
                    handleCollision((Consumable) checkCollision(player));
                }
            }
        }
    }

    // Spawn new Entities on the right edge of the game board
    protected void spawnEntities() {
        avoid = new Avoid(this.getWindowWidth(), rand.nextInt(this.getWindowHeight() - 100));
        get = new Get(this.getWindowWidth(), rand.nextInt(this.getWindowHeight() - 100));
        rget = new RareGet(this.getWindowWidth(), rand.nextInt(this.getWindowHeight() - 100));

        if (rand.nextInt(2) == 0) {
            displayList.add(avoid);
        } else if (rand.nextInt(2) == 1) {
            displayList.add(get);
        } else if (rand.nextInt(5) == 2) {
            displayList.add(rget);
        }

    }

    // Called whenever it has been determined that the Player collided with a
    // consumable
    protected void handleCollision(Consumable collidedWith) {
        player.modifyHP(collidedWith.getDamageValue());
        if (score + collidedWith.getPointsValue() >= 0) {
            score += collidedWith.getPointsValue();
        }
        displayList.remove((Entity) collidedWith);
        if (isGameOver()) {
            isPaused = true;
            postGame();
        }
    }

    // Called once the game is over, performs any end-of-game operations
    protected void postGame() {
        if (score >= SCORE_TO_WIN) {
            super.setTitleText("Game is over! You win!");
        }
        if (player.getHP() <= 0) {
            super.setTitleText("Game is over! You lost!");
        }
    }

    // Determines if the game is over or not
    // Game can be over due to either a win or lose state
    protected boolean isGameOver() {
        if (score >= SCORE_TO_WIN) {
            return true;
        }
        if (player.getHP() <= 0) {
            return true;
        }
        return false;
    }

    // Reacts to a single key press on the keyboard
    // Override's AbstractGame's handleKey
    protected void handleKey(int key) {
        // first, call AbstractGame's handleKey to deal with any of the
        // fundamental key press operations
        super.handleKey(key);
        setDebugText("Debug enabled: press D to disable");
        // if a splash screen is up, only react to the advance splash key
        if (getSplashImage() != null) {
            if (key == ADVANCE_SPLASH_KEY)
                super.setSplashImage(null);
            return;
        }
        // react to movement
        if (!isPaused) {
            if (key == downArrow
                    && player.getY() <= (this.getWindowHeight() - player.getHeight())) {
                player.setY(player.getY() + player.getMovementSpeed());
            } else if (key == upArrow && player.getY() > 0) {
                player.setY(player.getY() - player.getMovementSpeed());
            } else if (key == leftArrow && player.getX() != 0) {
                player.setX(player.getX() - player.getMovementSpeed());
            } else if (key == rightArrow
                    && player.getX() <= (this.getWindowWidth() - player.getWidth())) {
                player.setX(player.getX() + player.getMovementSpeed());
            }
        }
        // react to pause
        if (key == KeyEvent.VK_P && !isPaused) {
            isPaused = true;
        } else if (key == KeyEvent.VK_P && isPaused) {
            isPaused = false;
        }

        // react to speed up
        // uses equals instead of + because otherwise it would be shift + +
        if (key == KeyEvent.VK_EQUALS && getGameSpeed() < MAX_GAME_SPEED) {
            setGameSpeed(getGameSpeed() + SPEED_CHANGE);
        }
        if (key == KeyEvent.VK_MINUS && getGameSpeed() > SPEED_CHANGE) {
            setGameSpeed(getGameSpeed() - SPEED_CHANGE);
        }
    }

}
