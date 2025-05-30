package gameentity;

public class Beetle extends UserGameEntity {

    public Beetle(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        super.loadImages("BeetleUp1.png",
                "BeetleUp2.png",
                null,
                "BeetleDown1.png",
                "BeetleDown2.png",
                null,
                "BeetleRight1.png",
                "BeetleRight2.png",
                null,
                "BeetleLeft1.png",
                "BeetleLeft2.png",
                null);
    }
}
