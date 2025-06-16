package maptile;

import gameentity.GameSound;

import java.awt.image.BufferedImage;

public class MapTile {
    public BufferedImage image;
    public boolean isSolid;
    public boolean hasSound;
    public GameSound sound;
    public String name;

    public MapTile(String name, BufferedImage image, boolean isSolid, boolean hasSound) {
        this.name = name;
        this.image = image;
        this.isSolid = isSolid;
        this.hasSound = hasSound;
        this.sound = null;
    }

    public MapTile(String name, BufferedImage image, boolean isSolid, boolean hasSound, GameSound sound) {
        this.name = name;
        this.image = image;
        this.isSolid = isSolid;
        this.hasSound = hasSound;
        this.sound = sound;
    }
}
