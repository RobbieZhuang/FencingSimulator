package client;

import java.awt.Color;

public class PlayerImage {
	private String playerID;
	private double pX;
	private double pY;
	private int status;
	private Color playerColor;
	private Hitbox d;

	public PlayerImage (String playerID, Color playerColor) {
		d= new Hitbox(pX, pY, 100, 100);
		this.pX = 0;
		this.pY = 0;
		this.status = 0;
		this.playerID = playerID;
		this.playerColor = playerColor;
	}

	public String getPlayerID () {
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
