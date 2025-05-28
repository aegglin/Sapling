package maptile;

import window.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapTileManager {

    private static final int NUM_TILE_TYPES = 3;

    private GamePanel gamePanel;
    private MapTile[] mapTiles;

    public MapTileManager(GamePanel gamePanel) {
        mapTiles = new MapTile[NUM_TILE_TYPES];
        this.gamePanel = gamePanel;
        loadTileImages();
    }

    private MapTile loadTileImage(String imageFileName) {
        BufferedImage tileImage = null;
        try {
            tileImage = ImageIO.read(new File("assets/" + imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MapTile(tileImage);
    }

    public void loadTileImages() {
        mapTiles[0] = loadTileImage("Grass_16.png");
        mapTiles[1] = loadTileImage("Grass_16.png");
        mapTiles[2] = loadTileImage("Grass_16.png");
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(mapTiles[0].image, 0, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[1].image, 48, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[2].image, 96, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
