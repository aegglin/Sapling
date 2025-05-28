package gameentity;

import window.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bee extends GameEntity{

    public GamePanel gamePanel;

    public Bee(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        super.loadImages("BeeUpAnimated_16.png",
                "BeeDownAnimated_16.png",
                "BeeLeftAnimated_16.png",
                "BeeRightAnimated_16.png", true);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        // Switch on which image to draw
        switch (direction) {
            case UP:
                if (currentSpriteNumber == 1) {
                    image = up1;
                } else if (currentSpriteNumber == 2) {
                    image = up2;
                }
                break;
            case DOWN:
                if (currentSpriteNumber == 1) {
                    image = down1;
                } else if (currentSpriteNumber == 2) {
                    image = down2;
                }
                break;
            case LEFT:
                if (currentSpriteNumber == 1) {
                    image = left1;
                } else if (currentSpriteNumber == 2) {
                    image = left2;
                }
                break;
            case RIGHT:
                if (currentSpriteNumber == 1) {
                    image = right1;
                } else if (currentSpriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
