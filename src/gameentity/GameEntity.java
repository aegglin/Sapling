package gameentity;

import window.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GameEntity {

    public static final int SPRITE_SWITCH_THRESHOLD = 12;

    public int x, y, speed;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public Direction direction;

    public abstract void update();
    public abstract void draw(Graphics2D g2);

    public int spriteUpdateCount = 0;
    public int currentSpriteNumber = 1;

    public void loadImages(String upSpriteSheetFileName,
                           String downSpriteSheetFileName,
                           String leftSpriteSheetFileName,
                           String rightSpriteSheetFileName,
                           boolean hasSpecial) {

        try {
            BufferedImage upSpriteSheet = ImageIO.read(new File("assets/" + upSpriteSheetFileName));
            up1 = upSpriteSheet.getSubimage(0, 0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            up2 = upSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            if (hasSpecial) {
                up3 = upSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE * 2, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            }

            BufferedImage downSpriteSheet = ImageIO.read(new File("assets/" + downSpriteSheetFileName));            down1 = downSpriteSheet.getSubimage(0, 0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            down2 = downSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            if (hasSpecial) {
                down3 = downSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE * 2, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            }

            BufferedImage leftSpriteSheet = ImageIO.read(new File("assets/" + leftSpriteSheetFileName));
            left1 = leftSpriteSheet.getSubimage(0, 0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            left2 = leftSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            if (hasSpecial) {
                left3 = leftSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE * 2, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            }

            BufferedImage rightSpriteSheet = ImageIO.read(new File("assets/" + rightSpriteSheetFileName));            right1 = rightSpriteSheet.getSubimage(0, 0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            right2 = rightSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            if (hasSpecial) {
                right3 = rightSpriteSheet.getSubimage(0, GamePanel.ORIGINAL_TILE_SIZE * 2, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
