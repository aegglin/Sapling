package gameentity;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedReader;
import java.net.URL;

public class Sound {
    Clip clip; //opens an audio file
    URL soundURL[] = new URL[3];

    public Sound() {

//        BufferedReader br = new BufferedReader(new AudioInputStream())
        soundURL[0] = getClass().getResource("/assets/sounds/bees.wav");
        soundURL[1] = getClass().getResource("/assets/sounds/flies.wav");
        soundURL[2] = getClass().getResource("/assets/sounds/woodpecker.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.err.println("Error importing " + soundURL[i] + " at " + i);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
