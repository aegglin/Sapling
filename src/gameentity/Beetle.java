package gameentity;

import window.GamePanel;
import window.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static gameentity.Direction.DOWN;

public class Beetle extends GameEntity {

    public GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Beetle(int x, int y, int speed, Direction direction) {
        keyHandler = GamePanel.getKeyHandler();
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;

        super.loadImages("BeetleUpAnimated_16.png",
                "BeetleDownAnimated_16.png",
                "BeetleLeftAnimated_16.png",
                "BeetleRightAnimated_16.png",
                false);
    }

    @Override
    public void update() {
        if (keyHandler.keyPressed) {
            if (keyHandler.upPressed) {
                direction = Direction.UP;
                y -= speed;
            } else if (keyHandler.downPressed) {
                direction = DOWN;
                y += speed;
            } else if (keyHandler.leftPressed) {
                direction = Direction.LEFT;
                x -= speed;
            } else if (keyHandler.rightPressed) {
                direction = Direction.RIGHT;
                x += speed;
            }
            // count the number of times update has been called with the current sprite
            spriteUpdateCount++;

            // switch sprites if it has been the threshold number of frames with the other one
            if (spriteUpdateCount > SPRITE_SWITCH_THRESHOLD) {
                currentSpriteNumber = currentSpriteNumber == 1 ? 2 : 1;
                spriteUpdateCount = 0;
            }
        } else {
            // If we stop moving, don't stop on the sprite without legs
            if (currentSpriteNumber == 2) {
                currentSpriteNumber = 1;
                spriteUpdateCount = 0;
            }
        }
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
