package Sounds;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class getCoin {
    private static final String COPPER_COIN_SOUND = "Sounds/assets/copper.wav";
    private static final String SILVER_COIN_SOUND = "Sounds/assets/silver.wav";
    private static final String GOLD_COIN_SOUND = "Sounds/assets/gold.wav";
    private static final String RARE_COIN_SOUND = "Sounds/assets/rarecoin.wav";
    private static final String DAMAGE_SOUND = "Sounds/assets/avoid.wav";

    public static void copperCoin() {
        File copperCoin = new File(COPPER_COIN_SOUND);
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(copperCoin);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void silverCoin() {
        File silverCoin = new File(SILVER_COIN_SOUND);
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(silverCoin);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void goldCoin() {
        File goldCoin = new File(GOLD_COIN_SOUND);
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(goldCoin);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void rareCoin() {
        File rareCoin = new File(RARE_COIN_SOUND);
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(rareCoin);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void takeDamage() {
        File damageSound = new File(DAMAGE_SOUND);
        try {
            AudioInputStream AS = AudioSystem.getAudioInputStream(damageSound);
            Clip clip = AudioSystem.getClip();
            clip.open(AS);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
