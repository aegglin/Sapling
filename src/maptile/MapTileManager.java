package maptile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapTileManager {

    private MapTile[] mapTiles;



    public MapTileManager() {
        mapTiles = new MapTile[10];
    }

    public void loadMapTileImages() {
        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/BeetleLeftAnimated_32.png"));
            BufferedImage test = spritesheet.getSubimage(0, 0, 300, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
