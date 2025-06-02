package maptile;

import window.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapTileManager {

    private static final int NUM_TILES = 25;

    private GamePanel gamePanel;
    private MapTile[] mapTiles;

    public MapTileManager(GamePanel gamePanel) {
        mapTiles = new MapTile[NUM_TILES];
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
        mapTiles[0] = loadTileImage("Grass.png");
        mapTiles[1] = loadTileImage("Grass.png");
        mapTiles[2] = loadTileImage("Grass.png");
        mapTiles[3] = loadTileImage("Grass.png");
        mapTiles[4] = loadTileImage("Grass.png");

        mapTiles[5] = loadTileImage("Grass.png");
        mapTiles[6] = loadTileImage("Grass.png");
        mapTiles[7] = loadTileImage("Grass.png");
        mapTiles[8] = loadTileImage("Grass.png");
        mapTiles[9] = loadTileImage("Grass.png");

        mapTiles[10] = loadTileImage("Grass.png");
        mapTiles[11] = loadTileImage("Grass.png");
        mapTiles[12] = loadTileImage("Grass.png");
        mapTiles[13] = loadTileImage("Grass.png");
        mapTiles[14] = loadTileImage("Grass.png");

        mapTiles[15] = loadTileImage("Grass.png");
        mapTiles[16] = loadTileImage("Grass.png");
        mapTiles[17] = loadTileImage("Grass.png");
        mapTiles[18] = loadTileImage("Grass.png");
        mapTiles[19] = loadTileImage("Grass.png");

        mapTiles[20] = loadTileImage("Grass.png");
        mapTiles[21] = loadTileImage("Grass.png");
        mapTiles[22] = loadTileImage("Grass.png");
        mapTiles[23] = loadTileImage("Grass.png");
        mapTiles[24] = loadTileImage("Grass.png");
    }

    public void drawAll(Graphics2D g2) {
        g2.drawImage(mapTiles[0].image, 0, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[1].image, 48, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[2].image, 96, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[3].image, 144, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[4].image, 192, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        g2.drawImage(mapTiles[0].image, 0, 48, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[1].image, 48, 48, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[2].image, 96, 48, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[3].image, 144, 48, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[4].image, 192, 48, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        g2.drawImage(mapTiles[0].image, 0, 96, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[1].image, 48, 96, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[2].image, 96, 96, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[3].image, 144, 96, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[4].image, 192, 96, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        g2.drawImage(mapTiles[0].image, 0, 144, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[1].image, 48, 144, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[2].image, 96, 144, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[3].image, 144, 144, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[4].image, 192, 144, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);

        g2.drawImage(mapTiles[0].image, 0, 192, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[1].image, 48, 192, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[2].image, 96, 192, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[3].image, 144, 192, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        g2.drawImage(mapTiles[4].image, 192, 192, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
