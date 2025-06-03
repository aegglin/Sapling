package window;

import maptile.MapTileCollisionHandler;
import gameentity.Bee;
import gameentity.Beetle;
import gameentity.Direction;
import gameentity.UserGameEntity;
import maptile.MapTileHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
    public static final int WORLD_WIDTH = TILE_SIZE * NUMBER_WORLD_COLS;
    public static final int WORLD_HEIGHT = TILE_SIZE * NUMBER_WORLD_ROWS;

    private static final KeyHandler keyHandler = new KeyHandler();
    public MapTileHandler mapTileHandler;

    public MapTileCollisionHandler mapTileCollisionHandler;

    public UserGameEntity user;

    private Beetle beetle;
    private Bee bee1;
    private Bee bee2;
    private Bee bee3;

    private Thread gameThread;

    public GamePanel() {
        gameThread = new Thread(this);
        this.addKeyListener(keyHandler);

        // JPanel methods
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        mapTileHandler = new MapTileHandler(this);
        mapTileCollisionHandler = new MapTileCollisionHandler(this);

        beetle = new Beetle(1000, 1000, 4, Direction.DOWN, this);
        user = beetle;

        bee1 = new Bee(200, 200, 4, Direction.UP, this, user);
        bee2 = new Bee(300, 300, 4, Direction.LEFT, this, user);
        bee3 = new Bee(400, 400, 4, Direction.RIGHT, this, user);

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
        beetle.draw(g2);
        bee1.draw(g2);
        bee2.draw(g2);
        bee3.draw(g2);

        g2.dispose();
    }

    public void update() {
        beetle.update();
        bee1.update();
        bee2.update();
        bee3.update();
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
