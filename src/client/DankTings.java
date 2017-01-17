package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JPanel;

import graphics.SpriteSheet;
import graphics.SpriteSheetLoader;
import map.Land;
import map.RoomOutdoors;

public class DankTings extends JPanel implements KeyListener, Runnable {

	// MAKE MAP HERE
	private volatile static PlayerImage [] players = new PlayerImage[2];
	private String myPlayerID;
	private boolean running;

    // Sprites
    SpriteSheet spriteSheet = new SpriteSheet("/resources/SpriteSheet.png");

	private boolean [] clicked;
	long start;
	long end;
	long counter = 0;
	int fps = 0;

	RoomOutdoors r = new RoomOutdoors();

	public DankTings(String myPlayerID, String playerID2) {
		clicked = new boolean [4];
		System.out.println("Running DankTings");
		this.myPlayerID = myPlayerID;
		players[0] = new PlayerImage(myPlayerID, Color.ORANGE);
		players[1] = new PlayerImage(playerID2, Color.YELLOW);
		SpriteSheetLoader spriteSheetLoader = new SpriteSheetLoader(16, 16, 8, 8, spriteSheet);
		this.setLayout(null);
		this.setSize(2000, 600);
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.black);
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (int a = 0; a < 2; a++) {
			PlayerImage p = players[a];
			g.setColor(p.getPlayerColor());
			g.fillRect((int)p.getpX(), (int)p.getpY(), 25, 25);
			g.drawImage(SpriteSheetLoader.sprites[0][0], (int)p.getpX(), (int)p.getpY(), null);
		}
		g.setColor(Color.MAGENTA);
		LinkedList <Land> terrain = r.getTerrain();
		for (Land l: terrain) {
			g.fillRect(l.getlX(), l.getlY(), l.getLength(), l.getHeight());
		}


		g.drawString( fps + "", 50, 50);

	}

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
			}
		}
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
		int keyCode = e.getKeyCode();

			if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) && !clicked[0]){
				players[0].setpY(players[0].getpY()+3);
				Client.send("W");
				clicked[0] = true;
			} 
			if ((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) && !clicked[1]){

				players[0].setpY(players[0].getpY()+3);
				Client.send("A");
				clicked[1] = true;
			} 
			if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) && !clicked[2]){

				players[0].setpX(players[0].getpX()-3);
				Client.send("S");
				clicked[2] = true;
			} 
			if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) && !clicked[3]){

				players[0].setpX(players[0].getpX()+3);
				Client.send("D");
				clicked[3] = true;
			}
			if (keyCode == KeyEvent.VK_F) {
				Client.send("F");
			}
			if (keyCode == KeyEvent.VK_G) {
				Client.send("G");
			}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W  || keyCode == KeyEvent.VK_UP){
			System.out.println("#W");
			Client.send("#W");
			clicked[0] = false;
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			Client.send("#A");

			clicked[1] = false;

		}
		 if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			Client.send("#S");
			clicked[2] = false;

		}
		 if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			Client.send("#D");
			clicked[3] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

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

}
