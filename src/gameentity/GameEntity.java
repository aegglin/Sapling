package gameentity;

import window.GamePanel;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameEntity {

    public static final int SPRITE_SWITCH_THRESHOLD = 12;

    public int worldX, worldY, speed;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public Direction direction;
    public Rectangle collisionArea;
    public boolean isColliding;

    protected int spriteUpdateCount;
    protected int currentSpriteNumber;
    protected GamePanel gamePanel;

    protected abstract void update();
    protected abstract void draw(Graphics2D g2);
    protected abstract boolean isColliding(GameEntity gameEntity);


    public GameEntity(int worldX, int worldY, int speed, Direction direction, GamePanel gamePanel) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
        this.direction = direction;
        this.gamePanel = gamePanel;

        isColliding = false;
        spriteUpdateCount = 0;
        currentSpriteNumber = 1;
    }

    public GameEntity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void loadImages(String up1FileName,
                           String up2FileName,
                           String up3FileName,
                           String down1FileName,
                           String down2FileName,
                           String down3FileName,
                           String right1FileName,
                           String right2FileName,
                           String right3FileName,
                           String left1FileName,
                           String left2FileName,
                           String left3FileName) {

        try {
            up1 = ImageIO.read(new File("assets/" + up1FileName));
            up2 = ImageIO.read(new File("assets/" + up2FileName));
            if (up3FileName != null) {
                up3 = ImageIO.read(new File("assets/" + up3FileName));
            }
            down1 = ImageIO.read(new File("assets/" + down1FileName));
            down2 = ImageIO.read(new File("assets/" + down2FileName));
            if (down3FileName != null) {
                down3 = ImageIO.read(new File("assets/" + down3FileName));
            }
            right1 = ImageIO.read(new File("assets/" + right1FileName));
            right2 = ImageIO.read(new File("assets/" + right2FileName));
            if (right3FileName != null) {
                right3 = ImageIO.read(new File("assets/" + right3FileName));
            }
            left1 = ImageIO.read(new File("assets/" + left1FileName));
            left2 = ImageIO.read(new File("assets/" + left2FileName));
            if (left3FileName != null) {
                left3 = ImageIO.read(new File("assets/" + left3FileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}