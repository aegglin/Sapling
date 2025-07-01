package window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, keyPressed;
    public boolean isDebugMode;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            keyPressed = true;
            upPressed = true;
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            keyPressed = true;
            downPressed = true;
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            keyPressed = true;
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            keyPressed = true;
            rightPressed = true;
        } else if (keyCode == KeyEvent.VK_T) {
            isDebugMode = !isDebugMode;
            gamePanel.isDebugMode = isDebugMode;
        } else if (keyCode == KeyEvent.VK_P) {
            if (gamePanel.gameState == GamePanel.PLAY_STATE) {
                gamePanel.gameState = GamePanel.PAUSE_STATE;
            } else if (gamePanel.gameState == GamePanel.PAUSE_STATE) {
                gamePanel.gameState = GamePanel.PLAY_STATE;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            keyPressed = false;
            upPressed = false;
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            keyPressed = false;
            downPressed = false;
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            keyPressed = false;
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            keyPressed = false;
            rightPressed = false;
        }
    }
}
