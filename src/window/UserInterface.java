package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UserInterface {

    private static final int MESSAGE_THRESHOLD = 120;
    private static final DecimalFormat decimalFormatter = new DecimalFormat("#0.00");

    private final GamePanel gamePanel;
    private final Font notificationFont, congratulationsFont;
    private boolean hasMessage;
    private String message;
    private int messageCount;
    private double timePlayed;
    private Graphics2D g2;

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        notificationFont = new Font("Arial", Font.PLAIN, 20);
        congratulationsFont = new Font("Arial", Font.BOLD, 80);
        hasMessage = false;
        message = "";
        messageCount = 0;
    }

    public void showMessage(String text) {
        message = text;
        hasMessage = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (gamePanel.isGameOver) {
            g2.setFont(congratulationsFont);
            g2.setColor(Color.YELLOW);

            String winningText = "You won!";
            int textLength = (int)g2.getFontMetrics().getStringBounds(winningText, g2).getWidth();
            int winningTextX = GamePanel.SCREEN_WIDTH / 2 - textLength / 2;
            int winningTextY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE * 3);
            g2.drawString(winningText, winningTextX, winningTextY);

            String finishingTimeText = "Your time is " + decimalFormatter.format(timePlayed) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(finishingTimeText, g2).getWidth();
            int finishingTimeX = GamePanel.SCREEN_WIDTH / 2 - textLength / 2;
            int finishingTimeY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE * 4);
            g2.drawString(finishingTimeText, finishingTimeX, finishingTimeY);
        }
        else {
            g2.setFont(notificationFont);
            g2.setColor(Color.WHITE);

            if (gamePanel.gameState == GamePanel.PLAY_STATE) {
                timePlayed += (double)1/60; // called 60 times per second
            }
            if (gamePanel.gameState == GamePanel.PAUSE_STATE) {
                drawPauseMessage();
            }
            g2.drawString("Time: " + decimalFormatter.format(timePlayed), GamePanel.TILE_SIZE*14, 15);

            if (hasMessage) {
                g2.drawString(message, GamePanel.TILE_SIZE/2, GamePanel.TILE_SIZE*2);
                messageCount++;

                if (messageCount > MESSAGE_THRESHOLD) {
                    messageCount = 0;
                    hasMessage = false;
                }
            }
        }
    }

    public void drawPauseMessage() {
        String pauseMessage = "PAUSED";
        int length = (int)g2.getFontMetrics().getStringBounds(pauseMessage, g2).getWidth();
        int x = GamePanel.SCREEN_WIDTH / 2 - length  / 2;
        int y = GamePanel.SCREEN_HEIGHT / 2;

        g2.drawString(pauseMessage, x, y);
    }
}
