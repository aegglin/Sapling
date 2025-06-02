package gameentity;

public class Beetle extends UserGameEntity {

    public Beetle(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
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
