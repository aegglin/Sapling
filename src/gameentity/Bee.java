package gameentity;

public class Bee extends AIGameEntity{

    public Bee(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        super.loadImages("BeeUp1.png",
                "BeeUp2.png",
                "BeeUpFly.png",
                "BeeDown1.png",
                "BeeDown2.png",
                "BeeDownFly.png",
                "BeeRight1.png",
                "BeeRight2.png",
                "BeeRightFly.png",
                "BeeLeft1.png",
                "BeeLeft2.png",
                "BeeLeftFly.png");
    }
}
