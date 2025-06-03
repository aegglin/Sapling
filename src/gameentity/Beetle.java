package gameentity;

import window.GamePanel;

public class Beetle extends UserGameEntity {

    public Beetle(int worldX, int worldY, int speed, Direction direction, GamePanel gamePanel) {
        super(worldX, worldY, speed, direction, gamePanel);
        super.loadImages("beetle/BeetleUp1.png",
                "beetle/BeetleUp2.png",
                null,
                "beetle/BeetleDown1.png",
                "beetle/BeetleDown2.png",
                null,
                "beetle/BeetleRight1.png",
                "beetle/BeetleRight2.png",
                null,
                "beetle/BeetleLeft1.png",
                "beetle/BeetleLeft2.png",
                null);
    }
}
