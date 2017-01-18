package client;

import graphics.SpriteSheet;
import graphics.SpriteSheetLoader;
import map.Land;
import map.RoomOutdoors;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import map.Land;
import map.RoomOutdoors;

public class DankTings extends JPanel implements KeyListener {
<<<<<<< HEAD
	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH /4*3;
	
=======
	private int length = 700;
	private int height = 600;

public class DankTings extends JPanel implements KeyListener, Runnable {

>>>>>>> origin/master
	// MAKE MAP HERE
	private volatile static PlayerImage [] players = new PlayerImage[2];
    // Sprites
    SpriteSheet spriteSheet = new SpriteSheet("/resources/SpriteSheet.png");
	private volatile ArrayList <PlayerImage> players;
	private ClientSender sender;

	private byte keysPressed;

	long start;
	long end;
	long counter = 0;
	int fps = 0;
	RoomOutdoors r = new RoomOutdoors();
    private String myPlayerID;
    private boolean running;
    private boolean[] clicked;


	public DankTings(ClientSender sender, ArrayList <PlayerImage> players) {
		this.players = players;
		this.sender = sender;
		players[0] = new PlayerImage(myPlayerID, Color.ORANGE);
		players[1] = new PlayerImage(playerID2, Color.YELLOW);
		SpriteSheetLoader spriteSheetLoader = new SpriteSheetLoader(16, 16, 8, 8, spriteSheet);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.black);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int a = 0; a < players.size(); a++) {
			System.out.println("drawing rect");
			PlayerImage p = players.get(a);
			g.setColor(p.getPlayerColor());
			g.fillRect((int)p.getpX(), (int)p.getpY(), 25, 25);
		}

		g.setColor(Color.MAGENTA);
//		LinkedList <Land> terrain = r.getTerrain();
//		for (Land l: terrain) {
//			g.fillRect(l.getlX(), l.getlY(), l.getLength(), l.getHeight());
//		}


		g.drawString(fps + "", 50, 50);

	}

<<<<<<< HEAD
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
=======
	public synchronized void updatePlayer (String playerInfo) {
		String [] update = playerInfo.split(" ");
		String playerID = update[0];
		double pX = Double.parseDouble(update[1]);
		double pY = Double.parseDouble(update[2]);
		int status = Integer.parseInt(update[3]);
	public static synchronized void updatePlayer (String playerInfo) {
		String playerID = playerInfo.substring(0, playerInfo.indexOf(' '));
		playerInfo = playerInfo.substring(playerInfo.indexOf(' ')+1, playerInfo.length());


        double pX = Double.parseDouble(playerInfo.substring(0, playerInfo.indexOf(' ')));

		//System.out.println(pX);
		playerInfo = playerInfo.substring(playerInfo.indexOf(' ')+1, playerInfo.length());


        double pY = Double.parseDouble(playerInfo.substring(0, playerInfo.indexOf(' ')));
        playerInfo = playerInfo.substring(playerInfo.indexOf(' ')+1, playerInfo.length());
		int status = Integer.parseInt(playerInfo);

		for (int a = 0; a < 2; a++) {
			if (players[a].getPlayerID().equals(playerID)) {
				players[a].setpX(pX);
				players[a].setpY(pY);
//				players[a].setpX(players[a].getpX() + 1);

				players[a].setStatus(status);
		for (int a = 0; a < players.size(); a++) {
			PlayerImage p = players.get(a);
			if (p.getPlayerID().equals(playerID)) {
				p.setpX(pX);
				p.setpY(pY);
				p.setStatus(status);
			}
		}

		updateScreen();
	}
>>>>>>> origin/master

	//	public static void updatePlayers (String gameString){
	//		int numPlayers = (gameString.length() - gameString.replace(" ", "").length())/4;
	//		String[] strs = gameString.trim().split("\\s+");
	//		for (int i = 0; i < numPlayers; i ++){
	//				if (players)
	//			}
	//		}
	//	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int a = 0; a < 2; a++) {
            PlayerImage p = players[a];
            g.setColor(p.getPlayerColor());
            g.fillRect((int) p.getpX(), (int) p.getpY(), 25, 25);
            g.drawImage(SpriteSheetLoader.sprites[0][0], (int) p.getpX(), (int) p.getpY(), Client.SPRITE_SIZE, Client.SPRITE_SIZE, null);
        }
        g.setColor(Color.MAGENTA);
        LinkedList<Land> terrain = r.getTerrain();
        for (Land l : terrain) {
            g.fillRect(l.getlX(), l.getlY(), l.getLength(), l.getHeight());
        }


        g.drawString(fps + "", 50, 50);

    }

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

		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
			keysPressed = (byte) (keysPressed | (1<<0));
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			keysPressed = (byte) (keysPressed | (1<<1));
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			keysPressed = (byte) (keysPressed | (1<<2));
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			keysPressed = (byte) (keysPressed | (1<<3));
		}
		if (keyCode == KeyEvent.VK_F) {
			keysPressed = (byte) (keysPressed | (1<<4));
		}
		if (keyCode == KeyEvent.VK_G) {
			keysPressed = (byte) (keysPressed | (1<<5));
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W  || keyCode == KeyEvent.VK_UP){
			keysPressed = (byte) (keysPressed & ~(1<<0));
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			keysPressed = (byte) (keysPressed & ~(1<<1));
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			keysPressed = (byte) (keysPressed & ~(1<<2));
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			keysPressed = (byte) (keysPressed & ~(1<<3));
		}
		if (keyCode == KeyEvent.VK_F) {
			keysPressed = (byte) (keysPressed & ~(1<<4));
		}
		if (keyCode == KeyEvent.VK_G) {
			keysPressed = (byte) (keysPressed & ~(1<<5));
		}

		sender.send(keysPressed);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
<<<<<<< HEAD
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
=======

	private void updateScreen () {
		//		if (counter%10 == 0) {
		//		start = System.currentTimeMillis();
		//	}
		//	counter++;
		this.repaint();
		//	if (counter%10 == 0) {
		//		end = System.currentTimeMillis();
		//		fps = (int)(10*1000/(end - start));
		//		counter = 0;
		//	}
	public void go () {
		running = true;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (counter%10 == 0) {
				start = System.currentTimeMillis();
			}
			counter++;
			this.repaint();
			if (counter%10 == 0) {
				end = System.currentTimeMillis();
				fps = (int)(10*1000/(end - start));
				counter = 0;
			}
			System.out.println(players[0].dank(players[1]));
		}

	}

	public void addNewPlayer (String playerID) {
		players.add(new PlayerImage(playerID, new Color((int)Math.random()*256, (int)Math.random()*256, (int)Math.random()*256)));
	}
>>>>>>> origin/master
}
