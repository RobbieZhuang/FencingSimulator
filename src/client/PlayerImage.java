package client;

import java.awt.Color;

public class PlayerImage {
	private int playerID;
	private double pX;
	private double pY;
	private int status;
	private Color playerColor;
	
	public PlayerImage (int playerID, double x, double y, int status) {
		this.pX = x;
		this.pY = y;
		this.status = status;
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

	public void setpX(double pX2) {
		this.pX = pX2;
	}

	public double getpY() {
		return pY;
	}

	public void setpY(double pY) {
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
}
