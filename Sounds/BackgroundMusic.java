package Sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
    private static final String BG_MUSIC = "Sounds/assets/bgmusic.wav";
    private static final int LOOP_CONTINUOUSLY = -1;

    public static void main(String[] args) {
        music();
    }

    public static void music() {
        File music = new File(BG_MUSIC);
        // Initializes the background music and loops through it continuously
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);

            clip.loop(LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
