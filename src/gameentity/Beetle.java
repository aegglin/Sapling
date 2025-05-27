package gameentity;

import window.GamePanel;

import java.awt.*;

public class Beetle extends GameEntity {

    public GamePanel gamePanel;

    public Beetle(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void update() {
        if (GamePanel.keyHandler.upPressed) {
            y -= speed;
        } else if (GamePanel.keyHandler.downPressed) {
            y += speed;
        } else if (GamePanel.keyHandler.leftPressed) {
            x -= speed;
        } else if (GamePanel.keyHandler.rightPressed) {
            x -= speed;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.setColor(Color.YELLOW);
        g2.fillRect(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        g2.dispose();
    }
}
