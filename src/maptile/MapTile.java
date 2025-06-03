package maptile;

import java.awt.image.BufferedImage;

public class MapTile {
    public BufferedImage image;
    public boolean isSolid;

    public MapTile(BufferedImage image) {
        isSolid = false;
        this.image = image;
    }
}
