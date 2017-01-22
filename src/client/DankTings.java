package client;

import graphics.SpriteSheet;
import graphics.SpriteSheetLoader;
import map.Map;
import specialEffects.Rain;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DankTings extends JPanel implements KeyListener {
    public static final int WIDTH = Client.WIDTH;
    public static final int HEIGHT = Client.HEIGHT;
    public static final int SPRITE_SIZE = 50;
    public static final int SPRITE_ROWS = 2;
    public static final int SPRITE_COLUMNS = 32;
    public static final int SPRITE_PIXELS = 16;
    public static Map map;
    // Sprites
	SpriteSheet spriteSheet = new SpriteSheet("/resources/SpriteSheet.png");
	long start;
	long end;
	long counter = 0;
	int fps = 0;
    long startTime = System.currentTimeMillis();
    long elapsedTime = 0L;
    boolean walkingDouble;
	private PlayerImage [] players;
	private ClientSender sender;
	private byte keysPressed;
	private boolean running;
	private int myPlayerID;
	private int currentMap = 0;

	private Rain r = new Rain(2000, 500);


	public DankTings(int myPlayerID, ClientSender sender, PlayerImage[] players2) {
		r.go();
        map = new Map();
        this.myPlayerID = myPlayerID;
		this.players = players2;
		this.sender = sender;
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		SpriteSheetLoader spriteSheetLoader = new SpriteSheetLoader(SPRITE_PIXELS, SPRITE_PIXELS, SPRITE_ROWS, SPRITE_COLUMNS, spriteSheet);
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.black);
        setMap(0);
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cameraLX = 0;
		int cameraTY = 0;
		for (int b = 0; b < players.length; b++) {
			if (players[b].getPlayerID() == myPlayerID) {
				cameraLX = cameraLeftX((int)players[b].getpX());
				cameraTY = cameraTopY((int)players[b].getpY());
			}
		}

		// Making sure that only two players are drawn
		if (players.length < 3) {
			for (int a = 0; a < players.length; a++) {

				PlayerImage p = players[a];
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
                g.drawImage(SpriteSheetLoader.sprites[a][players[a].getStatus()], (int) p.getpX() - cameraLX, (int) p.getpY() - cameraTY, SPRITE_SIZE, SPRITE_SIZE, null);
               
			}
		}
		//		LinkedList <Land> terrain = r.getTerrain();
		//		for (Land l: terrain) {
		//			g.fillRect(l.getlX(), l.getlY(), l.getLength(), l.getHeight());
		//		}

		//
		//		g.drawString(fps + "", 50, 50);
		r.drawRain(g, cameraLX, cameraTY);
		map.getCurrentRoom().drawRoom(cameraLX, cameraTY, g);

	}
	
	public void setMap(int currentMap){
		this.currentMap = currentMap;
	}

	private int cameraLeftX (int pX) {
		int lX = pX - WIDTH/2;
		if (lX > map.getCurrentRoom().getLength() - WIDTH) {
			lX = map.getCurrentRoom().getLength() - WIDTH;
		} else if (lX < 0) {
			lX = 0;
		}
		return lX;
	}

	private int cameraTopY (int pY) {
		int tY = pY - HEIGHT/2;
		if (tY < 0) {
			tY = 0;
		} else if (tY > map.getCurrentRoom().getHeight()-HEIGHT) {
			tY = map.getCurrentRoom().getHeight()-HEIGHT;
		}
		return tY;
	}

	//	public synchronized void updatePlayer (String playerInfo) {
	//		String [] update = playerInfo.split(" ");
	//		String playerID = update[0];
	//		double pX = Double.parseDouble(update[1]);
	//		double pY = Double.parseDouble(update[2]);
	//		int status = Integer.parseInt(update[3]);
	//
	//		for (int a = 0; a < players.size(); a++) {
	//			PlayerImage p = players.get(a);
	//			if (p.getPlayerID().equals(playerID)) {
	//				p.setpX(pX);
	//				p.setpY(pY);
	//				p.setStatus(status);
	//			}
	//		}
	//		
	//		updateScreen();
	//	}

	//	public static void updatePlayers (String gameString){
	//		int numPlayers = (gameString.length() - gameString.replace(" ", "").length())/4;
	//		String[] strs = gameString.trim().split("\\s+");
	//		for (int i = 0; i < numPlayers; i ++){
	//				if (players)
	//			}
	//		}
	//	}

	@Override
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

		sender.send(keysPressed);
	}

	@Override
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

		sender.send(keysPressed);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	//
	//	private void updateScreen () {
	//		//		if (counter%10 == 0) {
	//		//		start = System.currentTimeMillis();
	//		//	}
	//		//	counter++;
	//		this.repaint();
	//		//	if (counter%10 == 0) {
	//		//		end = System.currentTimeMillis();
	//		//		fps = (int)(10*1000/(end - start));
	//		//		counter = 0;
	//		//	}
	//	}

	//	public void addNewPlayer (String playerID) {
	//		players.add(new PlayerImage(playerID, new Color((int)Math.random()*256, (int)Math.random()*256, (int)Math.random()*256)));
	//	}
}