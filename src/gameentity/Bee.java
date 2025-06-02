package gameentity;

public class Bee extends AIGameEntity{

    public Bee(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
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
