package client;

import java.awt.Color;

public class PlayerImage {
	private String playerID;
	private int pX;
	private int pY;
	private int status;
	private Color playerColor;
	
	public PlayerImage (String playerID, Color playerColor) {
		this.pX = 0;
		this.pY = 0;
		this.status = 0;
		this.playerID = playerID;
		this.playerColor = playerColor;
	}

	public String getPlayerID () {
		return playerID;
	}
	
	public int getpX() {
		return pX;
	}

	public void setpX(int pX) {
		this.pX = pX;
	}

	public int getpY() {
		return pY;
	}

	public void setpY(int pY) {
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
