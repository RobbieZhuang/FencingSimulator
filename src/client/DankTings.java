package client;

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
	private int length = 700;
	private int height = 600;
	
	// MAKE MAP HERE
	private volatile ArrayList <PlayerImage> players;
	private boolean running;
	private String myPlayerID;
	private ClientSender sender;

	private byte keysPressed;

	long start;
	long end;
	long counter = 0;
	int fps = 0;


	public DankTings(String myPlayerID, ClientSender sender) {
		this.myPlayerID = myPlayerID;
		players = new ArrayList<>();
		this.sender = sender;
		this.setLayout(null);
		this.setSize(length, height);
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.black);
	}

	@Override
	protected void paintComponent(Graphics g) {

		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (int a = 0; a < players.size(); a++) {
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

	public synchronized void updatePlayer (String playerInfo) {
		String [] update = playerInfo.split(" ");
		String playerID = update[0];
		double pX = Double.parseDouble(update[1]);
		double pY = Double.parseDouble(update[2]);
		int status = Integer.parseInt(update[3]);

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

		sender.send(keysPressed);
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
	}
	
	public void addNewPlayer (String playerID) {
		players.add(new PlayerImage(playerID, new Color((int)Math.random()*256, (int)Math.random()*256, (int)Math.random()*256)));
	}
}
