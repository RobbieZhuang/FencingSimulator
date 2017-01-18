package client;

import java.awt.Color;

public class PlayerImage {
	private int playerID;
	private double pX;
	private double pY;
	private int status;
	private Color playerColor;
<<<<<<< HEAD
	
	public PlayerImage (int playerID, double x, double y, int status) {
		this.pX = x;
		this.pY = y;
		this.status = status;
=======
	private Hitbox d;

	public PlayerImage (String playerID, Color playerColor) {
		d= new Hitbox(pX, pY, 100, 100);
		this.pX = 0;
		this.pY = 0;
		this.status = 0;
>>>>>>> origin/master
		this.playerID = playerID;
		this.playerColor = new Color((int)Math.random()*256, (int)Math.random()*256, (int)Math.random()*256);
		this.playerColor = Color.red;
	}

	public int getPlayerID () {
		return playerID;
	}
	
	public double getpX() {
		return pX;
	}

	public void setpX(double pX) {
		d.setHitBox(pX, pY);
		this.pX = pX;
	}

	public double getpY() {
		return pY;
	}

	public void setpY(double pY) {
		d.setHitBox(pX, pY);
		this.pY = pY;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Color getPlayerColor() {
		return playerColor;
	}
	
	public boolean dank(PlayerImage sa) {
		return d.collidesWith(sa.getD());
	}

	public Hitbox getD() {
		return d;
	}

}
