package window;

import gameentity.*;

import maptile.MapTileCollisionHandler;
import maptile.MapTileHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    public static final int RAW_PIXEL_TILE_SIZE = 16;
    private static final int SCALE = 3;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    private static final int FPS = 60;

    public static final int TILE_SIZE = RAW_PIXEL_TILE_SIZE * SCALE; // 48 x 48
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    public static final int NUMBER_WORLD_ROWS = 50;
    public static final int NUMBER_WORLD_COLS = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * NUMBER_WORLD_ROWS;
    public static final int WORLD_HEIGHT = TILE_SIZE * NUMBER_WORLD_COLS;

    public static final Random random = new Random();

    private static final KeyHandler keyHandler = new KeyHandler();
    public MapTileHandler mapTileHandler;
    public MapTileCollisionHandler mapTileCollisionHandler;
    public GameSoundManager gameSoundManager;

    public UserGameEntity user;

    private AIGameEntity[] aiGameEntities;
    private Beetle beetle;

    private Thread gameThread;

    public GamePanel() {
        gameThread = new Thread(this);
        this.addKeyListener(keyHandler);

        // JPanel methods
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        gameSoundManager = new GameSoundManager();
        mapTileHandler = new MapTileHandler(this);
        mapTileCollisionHandler = new MapTileCollisionHandler(this);

        beetle = new Beetle(1000, 1000, 4, Direction.DOWN, this);
        user = beetle;
        aiGameEntities = new AIGameEntity[6];
        for (int i = 0; i < aiGameEntities.length; i++) {
            Bee bee = new Bee(this, user);
            aiGameEntities[i] = bee;
        }
        GameSound ambiance = gameSoundManager.getSound("ambiance");
        gameSoundManager.play(ambiance, true);
        gameThread.start();
    }

    public static KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        mapTileHandler.drawAll(g2);
        for (AIGameEntity entity: aiGameEntities) {
            entity.draw(g2);
        }

        beetle.draw(g2);

        g2.dispose();
    }

    public void update() {
        beetle.update();
        for (AIGameEntity entity: aiGameEntities) {
            entity.update();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1e9 / FPS; //0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            //update positions and then repaint them in the correct locations
            update();
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime / 1e6; // Convert nanoseconds to milliseconds

            if (remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDrawTime += drawInterval;
        }
    }
}
