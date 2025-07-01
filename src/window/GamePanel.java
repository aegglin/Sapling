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
    public static final int SCALE = 3;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int FPS = 60;

    public static final int TILE_SIZE = RAW_PIXEL_TILE_SIZE * SCALE; // 48 x 48
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    public static final int NUMBER_WORLD_ROWS = 50;
    public static final int NUMBER_WORLD_COLS = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * NUMBER_WORLD_ROWS;
    public static final int WORLD_HEIGHT = TILE_SIZE * NUMBER_WORLD_COLS;

    public final static int PLAY_STATE = 1;
    public final static int PAUSE_STATE = 2;
    public int gameState;

    public static final Random random = new Random();
    public final KeyHandler keyHandler;

    public MapTileHandler mapTileHandler;
    public MapTileCollisionHandler mapTileCollisionHandler;
    public GameSoundHandler gameSoundHandler;
    public UserInterface userInterface;

    public UserGameEntity user;
    private AIGameEntity[] aiGameEntities;
    private Beetle beetle;
    public boolean isGameOver, isDebugMode;

    private Thread gameThread;

    public GamePanel() {
        isGameOver = false;
        isDebugMode = false;
        gameThread = new Thread(this);

        // JPanel methods
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        gameSoundHandler = new GameSoundHandler();
        mapTileHandler = new MapTileHandler(this);
        mapTileCollisionHandler = new MapTileCollisionHandler(this);
        keyHandler = new KeyHandler(this);

        this.addKeyListener(keyHandler);

        beetle = new Beetle(1000, 1000, 4, Direction.DOWN, this);
        user = beetle;
        aiGameEntities = new AIGameEntity[6];
        for (int i = 0; i < aiGameEntities.length; i++) {
            Bee bee = new Bee(this, user);
            aiGameEntities[i] = bee;
        }
        userInterface = new UserInterface(this);
        GameSound ambiance = gameSoundHandler.getSound("ambiance");
        gameSoundHandler.play(ambiance, true);

        gameState = PLAY_STATE;

        gameThread.start();
    }

    public void endGame() {
        gameThread = null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);

        long drawingStartTime = 0;
        if (isDebugMode) {
            drawingStartTime = System.nanoTime();
        }

        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        mapTileHandler.drawAll(g2);
        for (AIGameEntity entity: aiGameEntities) {
            entity.draw(g2);
        }

        beetle.draw(g2);
        userInterface.draw(g2);

        if (isDebugMode) {
            long drawingEndtime = System.nanoTime();
            long drawingTimeElapsed = drawingEndtime - drawingStartTime;
            g2.setColor(Color.WHITE);
            g2.drawString("Drawing time elapsed: " + drawingTimeElapsed, 10, 400);
            System.out.println("Drawing time elapsed: " + drawingTimeElapsed);
        }

        g2.dispose();
    }

    public void update() {

        if (gameState == PLAY_STATE) {
            beetle.update();
            for (AIGameEntity entity: aiGameEntities) {
                entity.update();
            }
        } else if (gameState == PAUSE_STATE) {

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
