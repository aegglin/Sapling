package maptile;

import java.awt.image.BufferedImage;

public class MapTile {
    public BufferedImage image;
    public boolean isSolid;
    public boolean hasSound;

    public MapTile(BufferedImage image, boolean isSolid, boolean hasSound) {
        this.image = image;
        this.isSolid = isSolid;
        this.hasSound = hasSound;
    }
}
