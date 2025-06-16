package gameentity;

import javax.sound.sampled.Clip;

public class GameSound {
    private Clip clip; //opens an audio file

    public GameSound(Clip clip) {
        this.clip = clip;
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
