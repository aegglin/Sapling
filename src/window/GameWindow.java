package window;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        GamePanel gamePanel = new GamePanel();

        this.add(gamePanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Sapling by Aiden Egglin");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

