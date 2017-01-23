/* PlayerImage.java
 *
 * Version 1.0
 * Andi Li, Bill Li, Max Gao, Robbie Zhuang 
 * 01-23-2017
 *
 * The image for the players with player data so the client can draw.
 */

package client;

import java.awt.Color;

public class PlayerImage {
	private int playerID;
	private double pX;
	private double pY;
	private int status;
	private Color playerColor;
	
	/**
	 * PlayerImage
	 * 
	 * constructor
	 * 
	 * @param playerID
	 * @param x
	 * @param y
	 * @param status
	 */
	public PlayerImage (int playerID, double x, double y, int status) {
		this.pX = x;
		this.pY = y;
		this.status = status;
		this.playerID = playerID;
		this.playerColor = new Color((int)Math.random()*256, (int)Math.random()*256, (int)Math.random()*256);
		this.playerColor = Color.red;
	}

	/**
	 * getPlayerID
	 * 
	 * The player's ID
	 * 
	 * @return int
	 */
	public int getPlayerID () {
		return playerID;
	}
	
	/**
	 * getpX
	 * 
	 * @return double, the x coordinate
	 */
	public double getpX() {
		return pX;
	}

	/**
	 * setPX2
	 * @param pX2
	 */
	public void setpX(double pX2) {
		this.pX = pX2;
	}

	/**
	 * getpY
	 * @return
	 */
	public double getpY() {
		return pY;
	}
	
	/**
	 * setpY
	 * @param pY
	 */
	public void setpY(double pY) {
		this.pY = pY;
	}

	/**
	 * getStatus
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * setStatus
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * getPlayerColour
	 * @return
	 */
	public Color getPlayerColor() {
		return playerColor;
	}
}