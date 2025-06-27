package maptile;

import gameentity.GameSound;
import window.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

// TODO: Should each tile have a boolean for if the beetle is on it?
// and then have a method for getting its neighbors (in a certain radius, keeping in mind that tiles on the edge may not have the full radius)

public class MapTileHandler {

    public int[][] mapTileNumbers;
    public MapTile[] mapTiles;

    private GamePanel gamePanel;
    private int currentTileIndex;
    private static final int NUM_TILES = 11;

    public MapTileHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mapTileNumbers = new int[GamePanel.NUMBER_WORLD_COLS][GamePanel.NUMBER_WORLD_ROWS];
        mapTiles = new MapTile[NUM_TILES];
        currentTileIndex = 0;
        loadTileImage("assets/tiles/Grass.png", "Grass", false, false, null);
        loadTileImage("assets/tiles/Tree1.png", "Tree1", true, false, null);
        loadTileImage("assets/tiles/Tree2.png", "Tree2", true, false, null);
        loadTileImage("assets/tiles/Tree3.png", "Tree3",true, false, null);
        loadTileImage("assets/tiles/Shrub.png", "Shrub",true, false, null);
        loadTileImage("assets/tiles/Underbrush.png", "Underbrush", false, false, null);
        loadTileImage("assets/tiles/Shrub_Underbrush.png", "ShrubUnderbrush", true, false, null);
        loadTileImage("assets/tiles/OrangeFlower.png", "OrangeFlower", false, false, null);
        loadTileImage("assets/tiles/Tree1_Flies1.png", "TreeFlies1", true, true, gamePanel.gameSoundHandler.getSound("flies"));
        loadTileImage("assets/tiles/Tree1_Beehive1.png", "TreeBeehive1", true, true, gamePanel.gameSoundHandler.getSound("bees"));
        loadTileImage("assets/tiles/Tree1_Woodpecker1.png", "TreeWoodpecker1", true, false, null);
        loadMap("assets/maps/map1.txt");
    }

    private void loadTileImage(String imageFileName, String name, boolean isSolid, boolean hasSound, GameSound sound) {
        BufferedImage tileImage = null;
        try {
            tileImage = ImageIO.read(new File(imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MapTile tile = new MapTile(name, tileImage, isSolid, hasSound, sound);
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
