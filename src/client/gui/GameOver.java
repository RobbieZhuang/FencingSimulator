/* GameOver.java
 *
 * The panel that does everything after game over
 */

package client.gui;

import client.Client;
import graphics.SpriteSheetLoader;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameOver extends JPanel {

    // Constants 
    private static int SPRITE_WIN_SIZE = 200;
    private static int SPRITE_LOSE_SIZE = 75;

    // Variables
    int playerNum;
    int winnerNum;
    BufferedImage background;
    BufferedImage[] winner = new BufferedImage[2];
    BufferedImage loser;
    long startTime;
    long elapsedTime = 0L;

    /**
     * Default constructor
     *
     * @param playerNum shows and int of which player the client is
     * @param winnerNum and int of who won
     */
    public GameOver(int playerNum, int winnerNum) {

        // Start timing and store player values
        startTime = System.currentTimeMillis();
        this.playerNum = playerNum;
        this.winnerNum = winnerNum;

        // Set up which backgrounds to draw
        if (playerNum == winnerNum) {
            // The client won
            background = Screen.spriteBackground.getWinBackground().getImage();
        } else {
            // The client lost
            background = Screen.spriteBackground.getLoseBackground().getImage();
        }

        // Set up which player image to draw
        if (winnerNum == 0) {
            // The winner is yellow
            winner[0] = SpriteSheetLoader.sprites[0][29];
            winner[1] = SpriteSheetLoader.sprites[0][30];
            loser = SpriteSheetLoader.sprites[1][31];
        } else {
            // The winner is purple
            winner[0] = SpriteSheetLoader.sprites[1][29];
            winner[1] = SpriteSheetLoader.sprites[1][30];
            loser = SpriteSheetLoader.sprites[0][31];
        }

        // Set up GUI
        setLayout(null);
        setSize(new Dimension(Client.WIDTH, Client.HEIGHT));
        setLayout(new BorderLayout());
        setBackground(Color.blue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);

        // Drawing background and loser
        g.drawImage(background, 0, 0, Client.WIDTH, Client.HEIGHT, null);
        g.drawImage(loser, Client.WIDTH - 2 * SPRITE_LOSE_SIZE, Client.HEIGHT / 2, SPRITE_LOSE_SIZE, SPRITE_LOSE_SIZE, null);

        // Drawing winner
        long limit = 500;
        elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime > 2 * limit) {
            startTime = System.currentTimeMillis();
        }

        if (elapsedTime / limit == 0) {
            // Winner sprite 1
            g.drawImage(winner[0], Client.WIDTH / 2 - SPRITE_WIN_SIZE / 2, Client.HEIGHT / 2, SPRITE_WIN_SIZE, SPRITE_WIN_SIZE, null);
        } else {
            // Winner sprite 2
            g.drawImage(winner[1], Client.WIDTH / 2 - SPRITE_WIN_SIZE / 2, Client.HEIGHT / 2, SPRITE_WIN_SIZE, SPRITE_WIN_SIZE, null);
        }
    }
}
