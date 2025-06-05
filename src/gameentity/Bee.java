package gameentity;

import window.GamePanel;

public class Bee extends AIGameEntity{

    public Bee(int worldX, int worldY, int speed, Direction direction, GamePanel gamePanel, UserGameEntity userGameEntity) {
        super(worldX, worldY, speed, direction, gamePanel, userGameEntity);
        super.loadImages("bee/BeeUp1.png",
                "bee/BeeUp2.png",
                "bee/BeeUpFly.png",
                "bee/BeeDown1.png",
                "bee/BeeDown2.png",
                "bee/BeeDownFly.png",
                "bee/BeeRight1.png",
                "bee/BeeRight2.png",
                "bee/BeeRightFly.png",
                "bee/BeeLeft1.png",
                "bee/BeeLeft2.png",
                "bee/BeeLeftFly.png");
    }

    public Bee(GamePanel gamePanel, UserGameEntity userGameEntity) {
        super(gamePanel, userGameEntity);
        super.loadImages("bee/BeeUp1.png",
                "bee/BeeUp2.png",
                "bee/BeeUpFly.png",
                "bee/BeeDown1.png",
                "bee/BeeDown2.png",
                "bee/BeeDownFly.png",
                "bee/BeeRight1.png",
                "bee/BeeRight2.png",
                "bee/BeeRightFly.png",
                "bee/BeeLeft1.png",
                "bee/BeeLeft2.png",
                "bee/BeeLeftFly.png");
    }
}
