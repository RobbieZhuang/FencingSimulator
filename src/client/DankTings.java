/* DankTings.java
 *
 * Version 1.0
 * Andi Li, Bill Li, Max Gao, Robbie Zhuang 
 * 01-23-2017
 *
 * This is code for a thread to receive messages on the client side
 */
package client;

import client.gui.GameOver;
import client.gui.Screen;
import graphics.SpriteSheetLoader;
import map.Map;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DankTings extends JPanel implements KeyListener {
    public static final int WIDTH = Client.WIDTH;
    public static final int HEIGHT = Client.HEIGHT;
    public static Map map;
    public static boolean waitInLobby;
    // -1 if game is still going on, 0 if 0 is winner, 1 if 1 is winner
    public static int winner;
    public static int canMoveToNextRoom;
    long start;
    long end;
    long counter = 0;
    int fps = 0;
    long startTime = System.currentTimeMillis();
    long elapsedTime = 0L;
    boolean walkingDouble;
    private Player[] players;
    private ClientSender sender;
    private byte keysPressed;
    private boolean running;
    private int myPlayerID;


    /**
     * Constructor
     * 
     * @param myPlayerID
     * @param sender
     * @param players2
     */
    public DankTings(int myPlayerID, ClientSender sender, Player[] players2) {
        // Displaying lobby if needed

        // Setting up the game
        map = new Map();
        this.myPlayerID = myPlayerID;
        this.players = players2;
        this.sender = sender;
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.black);
        waitInLobby = true;
        winner = -1;
        canMoveToNextRoom = -1;
    }


    @Override
    /**
     * paintComponent
     * draw things to the screen
     * @param Graphics
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (waitInLobby) {
            // Lobby
            g.drawImage(Screen.spriteBackground.getLobby().getImage(), 0, 0, Client.WIDTH, Client.HEIGHT, null);
        } else if (winner == -1) {
            // Game
            int cameraLX = 0;
            int cameraTY = 0;
            for (int b = 0; b < players.length; b++) {
                if (players[b].getPlayerID() == myPlayerID) {
                    cameraLX = cameraLeftX((int) players[b].getpX());
                    cameraTY = cameraTopY((int) players[b].getpY());
                }
            }

            // Drawing the room
            map.getCurrentRoom().drawRoom(cameraLX, cameraTY, g);

            // Drawing players
            for (int a = 0; a < players.length; a++) {

                Player p = players[a];
                if (p.getStatus() == 0) {
                    g.setColor(p.getPlayerColor());
                } else {
                    g.setColor(Color.MAGENTA);
                }

                // Walking animation
                if ((players[a].getStatus() > 15) && (players[a].getStatus() < 20)) {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);
                    if (elapsedTime >= 100) {
                        System.out.println(players[a].getStatus() + " " + walkingDouble);
                        walkingDouble = !walkingDouble;
                        if (walkingDouble) {
                            // The player switches walking animation
                            if (players[a].getStatus() == 16) {
                                players[a].setStatus(17);
                                walkingDouble = true;
                            } else if (players[a].getStatus() == 17) {
                                players[a].setStatus(16);
                                walkingDouble = false;
                            } else if (players[a].getStatus() == 18) {
                                players[a].setStatus(19);
                                walkingDouble = true;
                            } else if (players[a].getStatus() == 19) {
                                players[a].setStatus(18);
                                walkingDouble = false;
                            }
                        }

                        // Resetting the timer
                        startTime = System.currentTimeMillis();
                    }

                    if (walkingDouble) {
                        if (players[a].getStatus() == 16) {
                            players[a].setStatus(17);
                        } else if (players[a].getStatus() == 18) {
                            players[a].setStatus(19);
                        }
                    }
                }


                // Drawing the player
                g.drawImage(SpriteSheetLoader.sprites[a][players[a].getStatus()], (int) p.getpX() - cameraLX, (int) p.getpY() - cameraTY, Screen.SPRITE_SIZE, Screen.SPRITE_SIZE, null);

            }
        } else {
            // Game Over
            if ((winner == 0) || (winner == 1)) {
                Screen.switchComponent(new GameOver(myPlayerID, winner));
                winner = -2;
            }
        }
    }

    private int cameraLeftX(int pX) {
        int lX = pX - WIDTH / 2;
        if (lX > map.getCurrentRoom().getLength() - WIDTH) {
            lX = map.getCurrentRoom().getLength() - WIDTH;
        } else if (lX < 0) {
            lX = 0;
        }
        return lX;
    }

    private int cameraTopY(int pY) {
        int tY = pY - HEIGHT / 2;
        if (tY < 0) {
            tY = 0;
        } else if (tY > map.getCurrentRoom().getHeight() - HEIGHT) {
            tY = map.getCurrentRoom().getHeight() - HEIGHT;
        }
		return tY;
    }

    @Override
    /**
     * keyPressed
     */
    public void keyPressed(KeyEvent e) {
        // WASDFG
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            keysPressed = (byte) (keysPressed | (1 << 0));
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            keysPressed = (byte) (keysPressed | (1 << 1));
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            keysPressed = (byte) (keysPressed | (1 << 2));
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            keysPressed = (byte) (keysPressed | (1 << 3));
        }
        if (keyCode == KeyEvent.VK_F) {
            keysPressed = (byte) (keysPressed | (1 << 4));
        }
        if (keyCode == KeyEvent.VK_G) {
            keysPressed = (byte) (keysPressed | (1 << 5));
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            keysPressed = (byte) (keysPressed | (1 << 6));
        }

        sender.setMessage(keysPressed);
    }

    @Override
    /**
     * keyReleased
     */
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            keysPressed = (byte) (keysPressed & ~(1 << 0));
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            keysPressed = (byte) (keysPressed & ~(1 << 1));
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            keysPressed = (byte) (keysPressed & ~(1 << 2));
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            keysPressed = (byte) (keysPressed & ~(1 << 3));
        }
        if (keyCode == KeyEvent.VK_F) {
            keysPressed = (byte) (keysPressed & ~(1 << 4));
        }
        if (keyCode == KeyEvent.VK_G) {
            keysPressed = (byte) (keysPressed & ~(1 << 5));
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            keysPressed = (byte) (keysPressed & ~(1 << 6));
        }

        sender.setMessage(keysPressed);
    }

    @Override
    /**
     * keyTyped
     */
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}