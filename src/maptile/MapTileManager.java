package maptile;

import window.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapTileManager {

    private int[][] mapTileNumbers;
    private MapTile[] mapTiles;
    private GamePanel gamePanel;

    private static final int NUM_TILES = 4;

    private int currentTileIndex;

    public MapTileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mapTileNumbers = new int[GamePanel.NUMBER_WORLD_COLS][GamePanel.NUMBER_WORLD_ROWS];
        mapTiles = new MapTile[NUM_TILES];
        currentTileIndex = 0;
        loadTileImage("assets/tiles/Grass.png");
        loadTileImage("assets/tiles/Tree1.png");
        loadTileImage("assets/tiles/Tree2.png");
        loadTileImage("assets/tiles/Shrub.png");
        loadMap("assets/maps/map1.txt");
    }

    private void loadTileImage(String imageFileName) {
        BufferedImage tileImage = null;
        try {
            tileImage = ImageIO.read(new File(imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MapTile tile = new MapTile(tileImage);
        mapTiles[currentTileIndex] = tile;
        currentTileIndex++;
    }

    public void loadMap(String filePath) {
        try {
            File mapFile = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(mapFile));

            int col = 0;
            int row = 0;

            while (col < GamePanel.NUMBER_WORLD_COLS && row < GamePanel.NUMBER_WORLD_ROWS) {
                String line = br.readLine();
                while (col < GamePanel.NUMBER_WORLD_COLS) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumbers[col][row] = num;
                    col++;
                }
                if (col == GamePanel.NUMBER_WORLD_COLS) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawAll(Graphics2D g2) {

        for (int worldCol = 0; worldCol < GamePanel.NUMBER_WORLD_COLS; worldCol++) {
            for (int worldRow = 0; worldRow < GamePanel.NUMBER_WORLD_ROWS; worldRow++) {

                int tileNumber = mapTileNumbers[worldCol][worldRow];
                int worldX = worldCol * GamePanel.TILE_SIZE;
                int worldY = worldRow * GamePanel.TILE_SIZE;
                int cameraViewX = worldX - gamePanel.user.worldX + gamePanel.user.cameraViewX;
                int cameraViewY = worldY - gamePanel.user.worldY + gamePanel.user.cameraViewY;

                // Only draw tiles around the player, not all of them all the time
                if (worldX + GamePanel.TILE_SIZE > gamePanel.user.worldX - gamePanel.user.cameraViewX &&
                        worldX - GamePanel.TILE_SIZE < gamePanel.user.worldX + gamePanel.user.cameraViewX &&
                        worldY + GamePanel.TILE_SIZE > gamePanel.user.worldY - gamePanel.user.cameraViewY &&
                        worldY - GamePanel.TILE_SIZE < gamePanel.user.worldY + gamePanel.user.cameraViewY) {
                    g2.drawImage(mapTiles[tileNumber].image, cameraViewX, cameraViewY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
                }
            }
        }
    }
}
