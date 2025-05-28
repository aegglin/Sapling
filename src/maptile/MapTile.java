package maptile;

import java.awt.image.BufferedImage;

public class MapTile {
    public BufferedImage image;
    public boolean isColliding;

    public MapTile(BufferedImage image) {
        isColliding = false;
        this.image = image;
    }
}
