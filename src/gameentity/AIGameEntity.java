package gameentity;

import window.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AIGameEntity extends GameEntity{

    private UserGameEntity userGameEntity;
    private long lastUpdateTime;
    private int directionUpdateCount;

    protected final float SPECIAL_IMAGE_PROBABILITY_THRESHOLD = 0.2f;
    protected static final Random random = new Random();

    public AIGameEntity(int x, int y, int speed, Direction direction, GamePanel gamePanel, UserGameEntity userGameEntity) {
        super(x, y, speed, direction, gamePanel);
        this.userGameEntity = userGameEntity;
        lastUpdateTime = System.nanoTime();
        directionUpdateCount = 0;
        collisionArea = new Rectangle(10, 8, 16, 16);
    }

    public AIGameEntity(GamePanel gamePanel, UserGameEntity userGameEntity) {
        super(gamePanel);
        // subtract tile size to ensure there isn't an array index out of bounds error with the hearing area
        int randX = random.nextInt(GamePanel.WORLD_WIDTH-GamePanel.TILE_SIZE);
        int randY = random.nextInt(GamePanel.WORLD_HEIGHT-GamePanel.TILE_SIZE);

        int randSpeed = random.nextInt(10);
        Direction[] directions = Direction.values();
        Direction randDirection = directions[GamePanel.random.nextInt(directions.length)];
        super.worldX = randX;
        super.worldY = randY;
        super.speed = randSpeed;
        super.direction = randDirection;
        this.userGameEntity = userGameEntity;
        collisionArea = new Rectangle(10, 8, 16, 16);
    }


    private void setDirection() {
        Direction[] directions = Direction.values();
        Direction newDirection = directions[GamePanel.random.nextInt(directions.length)];

        // Try and smooth out AI motion a bit
        if (newDirection != direction && directionUpdateCount > 2) {
            direction = newDirection;
            directionUpdateCount = 0;
        } else {
            directionUpdateCount++;
        }
    }

    @Override
    public void update() {
        long newUpdateTime = System.nanoTime();
        if (newUpdateTime - lastUpdateTime >= 1e8) {
            setDirection();

            isColliding = false;
            super.gamePanel.mapTileCollisionHandler.checkCollision(this);

            if (!isColliding) {
                if (direction == Direction.UP) {
                    worldY -= speed;
                } else if (direction == Direction.DOWN) {
                    worldY += speed;
                } else if (direction == Direction.RIGHT) {
                    worldX += speed;
                } else if (direction == Direction.LEFT) {
                    worldX -= speed;
                }
            }

            lastUpdateTime = System.nanoTime();
        }
            // count the number of times update has been called with the current sprite
            spriteUpdateCount++;

            // switch sprites if it has been the threshold number of frames with the other one
            if (spriteUpdateCount > SPRITE_FRAME_SWITCH_THRESHOLD) {

                double specialImageProb = Math.random();

                // Switch to the special animation if it's under the threshold
                if (specialImageProb <= SPECIAL_IMAGE_PROBABILITY_THRESHOLD) {
                    currentSpriteNumber = 3;
                } else if (currentSpriteNumber == 1) {
                    currentSpriteNumber = 2;
                } else if (currentSpriteNumber == 2 || currentSpriteNumber == 3) {
                    currentSpriteNumber = 1;
                }
                spriteUpdateCount = 0;
            }
    }

    public void setAction() {

    }

    @Override
    public void draw(Graphics2D g2) {

        int cameraViewX = worldX - userGameEntity.worldX + userGameEntity.cameraViewX;
        int cameraViewY = worldY - userGameEntity.worldY + userGameEntity.cameraViewY;

        if (worldX + GamePanel.TILE_SIZE > userGameEntity.worldX - userGameEntity.cameraViewX &&
                worldX - GamePanel.TILE_SIZE < userGameEntity.worldX + userGameEntity.cameraViewX &&
                worldY + GamePanel.TILE_SIZE > userGameEntity.worldY - userGameEntity.cameraViewY &&
                worldY - GamePanel.TILE_SIZE < userGameEntity.worldY + userGameEntity.cameraViewY) {

                BufferedImage image = null;
                // Switch on which image to draw
                switch (direction) {
                    case UP:
                        if (currentSpriteNumber == 1) {
                            image = up1;
                        } else if (currentSpriteNumber == 2) {
                            image = up2;
                        } else if (currentSpriteNumber == 3) {
                            image = up3;
                        }
                        break;
                    case DOWN:
                        if (currentSpriteNumber == 1) {
                            image = down1;
                        } else if (currentSpriteNumber == 2) {
                            image = down2;
                        } else if (currentSpriteNumber == 3) {
                            image = down3;
                        }
                        break;
                    case LEFT:
                        if (currentSpriteNumber == 1) {
                            image = left1;
                        } else if (currentSpriteNumber == 2) {
                            image = left2;
                        } else if (currentSpriteNumber == 3) {
                            image = left3;
                        }
                        break;
                    case RIGHT:
                        if (currentSpriteNumber == 1) {
                            image = right1;
                        } else if (currentSpriteNumber == 2) {
                            image = right2;
                        } else if (currentSpriteNumber == 3) {
                            image = right3;
                        }
                        break;
                }
                g2.drawImage(image, cameraViewX, cameraViewY, null);
                if (super.gamePanel.isDebugMode) {
                    g2.setColor(Color.red);
                    g2.drawRect(cameraViewX + collisionArea.x, cameraViewY + collisionArea.y, collisionArea.width, collisionArea.height);
                }
        }
    }
    @Override
    public boolean isColliding(GameEntity gameEntity) {
        return false;
    }
}
