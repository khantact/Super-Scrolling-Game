//Contains main... run me to launch the game!
public class Launcher {

    // Initializes and launches the game
    public static void main(String[] args) {
        AbstractGame game = new BasicGame(800, 600);
        game.setDebugMode(true);
        game.play();
    }

}
