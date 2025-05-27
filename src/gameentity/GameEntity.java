package gameentity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameEntity {
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, fly;
    public Direction direction;

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}
