package gameentity;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;

public class GameSoundManager {

    private HashMap<String, GameSound> gameSounds;

    public GameSoundManager() {
        gameSounds = new HashMap<>();
        loadAudioFile("bees", "assets/sounds/bees.wav");
        loadAudioFile("ambiance", "assets/sounds/ambiance.wav");
    }

    public GameSound getSound(String name) {
        return gameSounds.get(name);
    }

    public void loadAudioFile(String name, String soundPath) {
        try {
            File soundFile = new File(soundPath);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            GameSound sound = new GameSound(clip);
            gameSounds.put(name, sound);
        } catch (Exception e) {
            System.err.println("Error importing sound for " + name + " at " + soundPath);
            e.printStackTrace();
        }
    }

    public void play(GameSound sound, boolean isLoop) {
        if (isLoop) {
            sound.loop();
        } else {
            sound.play();
        }
    }

    public void stop(GameSound sound) {
        sound.stop();
    }
}
