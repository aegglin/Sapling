package maptile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class MapTile {
    public BufferedImage image;
    public boolean isColliding;

    public MapTile(BufferedImage image) {
        isColliding = false;
        this.image = image;
    }
}
