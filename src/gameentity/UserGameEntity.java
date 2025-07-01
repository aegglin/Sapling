package gameentity;

import maptile.MapTile;
import window.GamePanel;
import window.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class UserGameEntity extends GameEntity {

    public GamePanel gamePanel;
    public final int cameraViewX, cameraViewY;
    public Rectangle hearingArea;
    public boolean isInEarshot;

    public UserGameEntity(int worldX, int worldY, int speed, Direction direction, GamePanel gamePanel) {
        super(worldX, worldY, speed, direction, gamePanel);

        collisionArea = new Rectangle(10, 8, 16, 16);
        hearingArea = new Rectangle(10, 8, 16, 16);
        isInEarshot = false;

        int offset = GamePanel.TILE_SIZE / 2; // Since the drawing starts at the top left corner of the tile

        cameraViewX = GamePanel.SCREEN_WIDTH / 2 - offset;
        cameraViewY = GamePanel.SCREEN_HEIGHT / 2 - offset;
    }

    @Override
    public void update() {
        if (super.gamePanel.keyHandler.keyPressed) {
            // Get the direction of movement
            if (super.gamePanel.keyHandler.upPressed) {
                direction = Direction.UP;
            } else if (super.gamePanel.keyHandler.downPressed) {
                direction = Direction.DOWN;
            } else if (super.gamePanel.keyHandler.leftPressed) {
                direction = Direction.LEFT;
            } else if (super.gamePanel.keyHandler.rightPressed) {
                direction = Direction.RIGHT;
            }

            // reset the collision
            isColliding = false;
            isInEarshot = false;
            int[] earshotTileNumbers = super.gamePanel.mapTileCollisionHandler.checkEarshot(this);
            super.gamePanel.mapTileCollisionHandler.checkCollision(this);

            // Player can only move when there isn't a collision
            if (!isColliding) {
                switch(direction) {
                    case Direction.UP:
                        worldY -= speed;
                        break;
                    case Direction.DOWN:
                        worldY += speed;
                        break;
                    case Direction.LEFT:
                        worldX -= speed;
                        break;
                    case Direction.RIGHT:
                        worldX += speed;
                        break;
                }
            }

            if (isInEarshot) {
                // if there are two sounds, determine which one to play
                int maxEarshotTileNumber = Math.max(earshotTileNumbers[0], earshotTileNumbers[1]);
                System.out.println("earshot max: " + maxEarshotTileNumber);
                MapTile hearingTile = super.gamePanel.mapTileHandler.mapTiles[maxEarshotTileNumber];
                System.out.println(hearingTile.name);
                super.gamePanel.gameSoundHandler.play(hearingTile.sound, false);
                super.gamePanel.userInterface.showMessage("Bees are buzzing");
            }

            // count the number of times update has been called with the current sprite
            spriteUpdateCount++;

            // switch sprites if it has been the threshold number of frames with the other one
            if (spriteUpdateCount > SPRITE_FRAME_SWITCH_THRESHOLD) {
                currentSpriteNumber = currentSpriteNumber == 1 ? 2 : 1;
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
        g2.drawImage(image, cameraViewX, cameraViewY, null);
        if (super.gamePanel.isDebugMode) {
            g2.setColor(Color.red);
            g2.drawRect(cameraViewX + collisionArea.x, cameraViewY + collisionArea.y, collisionArea.width, collisionArea.height);
        }
    }

    @Override
    public boolean isColliding(GameEntity gameEntity) {
        return false;
    }
}
