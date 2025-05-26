package maptile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MapTileManager {

    private MapTile[] mapTiles;

    public MapTileManager() {
        mapTiles = new MapTile[10];
    }

    public void loadMapTileImages() {
        try {
//            BufferedImage spritesheet = ImageIO.read(new File("assets/BeetleLeftAnimated_16.png"));
//            BufferedImage beetle1 = spritesheet.getSubimage(0, 0, 16, 16);
//            BufferedImage beetle2 = spritesheet.getSubimage(0, 16, 16, 16);

            BufferedImage grass = ImageIO.read(new File("assets/Grass_16.png"));
            MapTile tile = new MapTile(grass);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
