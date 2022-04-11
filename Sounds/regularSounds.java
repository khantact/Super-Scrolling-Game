package Sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class regularSounds {
    private static final String POINTS_CHECKPOINT = "Sounds/assets/every100pts.wav";
    private static final String WIN_GAME = "Sounds/assets/winSound.wav";
    private static final String LOSE_GAME = "Sounds/assets/loseSound.wav";

    public static void checkPoint() {
        File checkpoint = new File(POINTS_CHECKPOINT);
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(checkpoint);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void gameState(boolean state) {
        File gamestate = null;
        try {
            if (state) {
                gamestate = new File(WIN_GAME);
            } else {
                gamestate = new File(LOSE_GAME);
            }
            AudioInputStream AS = AudioSystem.getAudioInputStream(gamestate);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
