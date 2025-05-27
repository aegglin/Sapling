package window;

import gameentity.Beetle;
import maptile.MapTileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int SCALE = 3;
    private static final int MAX_SCREEN_COL = 16;
    private static final int MAX_SCREEN_ROW = 12;
    private static final int FPS = 60;

    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48 x 48
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    public static final KeyHandler keyHandler = new KeyHandler();

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

        beetle = new Beetle(100, 100, 4);

        mapTileManager = new MapTileManager();
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        beetle.draw(g2);
        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            beetle.update();
            repaint();
        }
    }
}
