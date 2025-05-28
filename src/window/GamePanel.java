package window;

import gameentity.Beetle;
import gameentity.Direction;
import maptile.MapTileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    public static final int ORIGINAL_TILE_SIZE = 16;
    private static final int SCALE = 3;
    private static final int MAX_SCREEN_COL = 16;
    private static final int MAX_SCREEN_ROW = 12;
    private static final int FPS = 60;

    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48 x 48
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    private static final KeyHandler keyHandler = new KeyHandler();

    public Beetle beetle;

    private Thread gameThread;
    private MapTileManager mapTileManager;

    public GamePanel() {
        gameThread = new Thread(this);
        this.addKeyListener(keyHandler);

        // JPanel methods
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        beetle = new Beetle(100, 100, 4, Direction.DOWN);

        mapTileManager = new MapTileManager(this);
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

        mapTileManager.drawAll(g2);
        beetle.draw(g2);

        g2.dispose();
    }

    public void update() {
        beetle.update();
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
